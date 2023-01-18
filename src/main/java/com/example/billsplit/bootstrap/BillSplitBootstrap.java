package com.example.billsplit.bootstrap;

import com.example.billsplit.domain.Currency;
import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
public class BillSplitBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserGroupRepository userGroupRepository;

    public BillSplitBootstrap(CurrencyRepository currencyRepository,
                              UserGroupRepository userGroupRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.userGroupRepository = userGroupRepository;
    }

    private void saveAll() {
        Uzer Aman = new Uzer();
        Aman.setName("Aman@gmail.com");

        Uzer Priya = new Uzer();
        Priya.setName("Priya@gmail.com");

        Uzer Alok = new Uzer();
        Alok.setName("Alok@gmail.com");

        Uzer Mohit = new Uzer();
        Mohit.setName("Mohit@gmail.com");

        Uzer Bhavna = new Uzer();
        Bhavna.setName("Bhavna@gmail.com");

        Uzer Prachi = new Uzer();
        Prachi.setName("Prachi@gmail.com");

        Uzer Twinkle = new Uzer();
        Twinkle.setName("Twinkle@gmail.com");

        Uzer Mimi = new Uzer();
        Mimi.setName("Mimi@gmail.com");

        UserGroup g1 = new UserGroup();
        g1.setTitle("DisneyLand");
        g1.setDescription("Trip to DisneyLand tokyo");

        userGroupRepository.save(g1);

        Set<Uzer> userList1 = new HashSet<>();
        Mohit.addUserGroup(g1);
        userList1.add(Mohit);
        Bhavna.addUserGroup(g1);
        userList1.add(Bhavna);
        Aman.addUserGroup(g1);
        userList1.add(Aman);
        Priya.addUserGroup(g1);
        userList1.add(Priya);
        userRepository.saveAll(userList1);

        g1.setUsers(userList1);

        userGroupRepository.save(g1);

        UserGroup g2 = new UserGroup();
        g2.setTitle("LasVegas");
        g2.setDescription("Trip to the sin city");

        userGroupRepository.save(g2);

        Set<Uzer> userList2 = new HashSet<>();
        Prachi.addUserGroup(g2);
        userList2.add(Prachi);
        Twinkle.addUserGroup(g2);
        userList2.add(Twinkle);
        Mimi.addUserGroup(g2);
        userList2.add(Mimi);
        Alok.addUserGroup(g2);
        userList2.add(Alok);
        Bhavna.addUserGroup(g2);
        userList2.add(Bhavna);

        userRepository.saveAll(userList2);

        g2.setUsers(userList2);


        userGroupRepository.save(g2);

        Currency inr = new Currency();
        inr.setName("INR");

        Currency usd = new Currency();
        usd.setName("USD");

        Currency euro = new Currency();
        euro.setName("EURO");

        Currency krw = new Currency();
        krw.setName("KRW");

        currencyRepository.save(inr);
        currencyRepository.save(usd);
        currencyRepository.save(euro);
        currencyRepository.save(krw);
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveAll();
    }
}
