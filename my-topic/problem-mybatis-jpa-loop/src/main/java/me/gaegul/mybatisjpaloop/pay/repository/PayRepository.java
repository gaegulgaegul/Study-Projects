package me.gaegul.mybatisjpaloop.pay.repository;

import me.gaegul.mybatisjpaloop.pay.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, String> {
}
