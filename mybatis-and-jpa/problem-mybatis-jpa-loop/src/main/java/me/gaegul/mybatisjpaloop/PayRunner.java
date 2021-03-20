package me.gaegul.mybatisjpaloop;

import me.gaegul.mybatisjpaloop.pay.model.Pay;
import me.gaegul.mybatisjpaloop.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PayRunner implements ApplicationRunner {

    @Autowired
    PayService payService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Map<String,Object>> params = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<>();
        map1.put("product", "product1");
        params.add(map1);

        Map<String,Object> map2 = new HashMap<>();
        map2.put("product", "product2");
        params.add(map2);

        Map<String,Object> map3 = new HashMap<>();
        map3.put("product", "product3");
        params.add(map3);

        payService.saves(params).forEach(System.out::println);

    }
}
