package me.gaegul.springbootmybatisjpaproperties.user.repository;

import me.gaegul.springbootmybatisjpaproperties.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
