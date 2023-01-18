package com.example.billsplit.security.service;

import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.util.Constant;
import com.example.billsplit.security.authentication.GmailAuthToken;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.*;

@Service
public class GmailAuthenticationProviderService {

    @Autowired
    private RestTemplate restTemplate;

    private void openDefaultBrower() {
        String url = "https://accounts.google.com/o/oauth2/v2/auth?" +
                "response_type=code&" +
                "client_id="+ Constant.CLIENT_ID +"&" +
                "scope=openid%20email&" +
                "access_type=offline&" +
                "redirect_uri=" + Constant.HTTPS_LOCALHOST_8080;

        String os = System.getProperty("os.name").toLowerCase();

        try {
            Runtime rt = Runtime.getRuntime();

            if(os.indexOf("win") >= 0) {
                // Windows
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if(os.indexOf("mac") >= 0) {
                // Mac
                rt.exec(new String[] {"open " + url});
            } else if(os.indexOf("nix") >=0 || os.indexOf("nux") >=0) {
                // Linux based
                String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                        "netscape", "opera", "links", "lynx" };
                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++)
                    if(i == 0)
                        cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
                    else
                        cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
                // If the first didn't work, try the next browser and so on
                rt.exec(new String[] { "sh", "-c", cmd.toString() });
            } else {
                System.out.println("Not able to open the default browser.\n Please open the below url:\n" + url);
            }
        }
        catch(IOException ex) {
            throw new BillSplitException("Exception " + ex.getMessage(), ex);
        }
    }

    public String getAuthorizationCode() {

        AtomicReference<String> authCode = new AtomicReference<>("");
        var latch = new CountDownLatch(1);
        try {
            //initialize the HTTPS server
            HttpsServer HTTPS_Server = HttpsServer.create(new InetSocketAddress(Constant.LOCALHOST, 8080), 0);
            SSLContext SSL_Context = SSLContext.getInstance("TLS");

            // initialise the keystore
            char[] Password = Constant.PASSWORD.toCharArray();
            KeyStore Key_Store = KeyStore.getInstance("JKS");
            FileInputStream Input_Stream = new FileInputStream(Constant.JKS_KEY_PATH);
            Key_Store.load(Input_Stream, Password);

            // setup the key manager factory
            KeyManagerFactory Key_Manager = KeyManagerFactory.getInstance("SunX509");
            Key_Manager.init(Key_Store, Password);

            // setup the trust manager factory
            TrustManagerFactory Trust_Manager = TrustManagerFactory.getInstance("SunX509");
            Trust_Manager.init(Key_Store);


            // setup the HTTPS context and parameters
            SSL_Context.init(Key_Manager.getKeyManagers(), Trust_Manager.getTrustManagers(), null);
            HTTPS_Server.setHttpsConfigurator(new HttpsConfigurator(SSL_Context) {
                public void configure(HttpsParameters params) {
                    try {
                        // initialise the SSL context
                        SSLContext SSL_Context = getSSLContext();
                        SSLEngine SSL_Engine = SSL_Context.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(SSL_Engine.getEnabledCipherSuites());
                        params.setProtocols(SSL_Engine.getEnabledProtocols());

                        // Set the SSL parameters
                        SSLParameters SSL_Parameters = SSL_Context.getSupportedSSLParameters();
                        params.setSSLParameters(SSL_Parameters);
                    } catch (Exception ex) {
                        throw  new BillSplitException("Failed to create the HTTPS port");
                    }
                }
            });
            HTTPS_Server.createContext("/", exchange -> {
                //read code
                String code = exchange.getRequestURI().toString().split("&")[0].split("=")[1];
                System.out.println("code = " + code);
                authCode.set(code);
                String response = "Code : " + code;
                exchange.sendResponseHeaders(200, response.length());
                exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
                latch.countDown();
            });
            HTTPS_Server.setExecutor(null); // creates a default executor
            HTTPS_Server.start();
            //open the browser
            openDefaultBrower();
            latch.await(60, TimeUnit.SECONDS);
            HTTPS_Server.stop(0);
            return authCode.get();
        } catch (IOException | InterruptedException | NoSuchAlgorithmException | KeyStoreException | CertificateException
                | UnrecoverableKeyException | KeyManagementException ex) {
           throw new RuntimeException(ex);
        }
    }

    public GmailAuthToken getGmailIDTokenAccessToken(final String code) {
        try {
            String url = "https://oauth2.googleapis.com/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setBasicAuth(Constant.CLIENT_ID,Constant.CLIENT_SECRET);

            String decodedString = code.replace("%2F","/");

            MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
            form.add("code", decodedString);
            form.add("redirect_uri", Constant.HTTPS_LOCALHOST_8080);
            form.add("grant_type", Constant.AUTHORIZATION_CODE);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(form, headers);

            var response = restTemplate.postForEntity(url, entity, GmailAuthToken.class);
            return response.getBody();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
