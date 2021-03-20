package me.gaegul.mybatisjpaloop.pay.service;

import lombok.RequiredArgsConstructor;
import me.gaegul.mybatisjpaloop.pay.mapper.PayMapper;
import me.gaegul.mybatisjpaloop.pay.model.Pay;
import me.gaegul.mybatisjpaloop.pay.repository.PayRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayMapper payMapper;
    private final PayRepository payRepository;

    public List<Pay> saves(List<Map<String,Object>> list) {
        List<Pay> pays = new ArrayList<>();

        for (Map<String,Object> map : list) {
            Pay pay = new Pay();
            pay.setId(payMapper.selectNewId(map));
            pay.setProduct(String.valueOf(map.get("product")));
            pays.add(payRepository.saveAndFlush(pay));
        }

        return Optional.ofNullable(pays).orElse(null);
    }

}
