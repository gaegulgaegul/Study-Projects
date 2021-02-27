package me.gaegul.mybatisjpa.user.repository;

import me.gaegul.mybatisjpa.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
