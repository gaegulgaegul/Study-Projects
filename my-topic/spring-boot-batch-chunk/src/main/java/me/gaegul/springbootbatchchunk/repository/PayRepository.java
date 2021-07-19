package me.gaegul.springbootbatchchunk.repository;

import me.gaegul.springbootbatchchunk.model.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
}
