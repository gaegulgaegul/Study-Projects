package me.whiteship.redisspringbootstarter.account;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("accounts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String Id;

    private String username;

    private String email;

}
