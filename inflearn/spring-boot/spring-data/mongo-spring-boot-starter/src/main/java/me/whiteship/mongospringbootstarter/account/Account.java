package me.whiteship.mongospringbootstarter.account;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String id;

    private String username;

    private String email;

}
