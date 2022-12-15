package com.example.billsplit.bootstrap;

import com.example.billsplit.domain.Currency;
import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        Aman.setName("Aman");

        Uzer Priya = new Uzer();
        Priya.setName("Priya");

        Uzer Alok = new Uzer();
        Alok.setName("Alok");

        Uzer Mohit = new Uzer();
        Mohit.setName("Mohit");

        Uzer Bhavna = new Uzer();
        Bhavna.setName("Bhavna");

        Uzer Prachi = new Uzer();
        Prachi.setName("Prachi");

        Uzer Twinkle = new Uzer();
        Twinkle.setName("Twinkle");

        Uzer Mimi = new Uzer();
        Mimi.setName("Mimi");

        UserGroup g1 = new UserGroup();
        g1.setTitle("DisneyLand");
        g1.setDescription("Trip to DisneyLand tokyo");


        List<Uzer> userList1 = new ArrayList<>();
        Mohit.setUserGroup(g1);
        userList1.add(Mohit);
        Bhavna.setUserGroup(g1);
        userList1.add(Bhavna);
        Aman.setUserGroup(g1);
        userList1.add(Aman);
        Priya.setUserGroup(g1);
        userList1.add(Priya);
        userRepository.saveAll(userList1);

        g1.setUsers(userList1);

        UserGroup g2 = new UserGroup();
        g2.setTitle("LasVegas");
        g2.setDescription("Trip to the sin city");
        List<Uzer> userList2 = new ArrayList<>();
        Prachi.setUserGroup(g2);
        userList2.add(Prachi);
        Twinkle.setUserGroup(g2);
        userList2.add(Twinkle);
        Mimi.setUserGroup(g2);
        userList2.add(Mimi);
        Alok.setUserGroup(g2);
        userList2.add(Alok);

        userRepository.saveAll(userList2);

        g2.setUsers(userList2);

        userGroupRepository.save(g1);
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
