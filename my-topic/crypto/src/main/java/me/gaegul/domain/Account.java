package me.gaegul.domain;

import lombok.Setter;
import lombok.ToString;
import me.gaegul.annotation.TestAnnotation;
import me.gaegul.crypto.Crypto;
import me.gaegul.crypto.CryptoType;

@ToString
@Setter
public class Account {

    private Long id;

    @TestAnnotation
    private String name;

    @Crypto(type = CryptoType.BCRYPT)
    private String password;

    @Crypto(type = CryptoType.AES256)
    private String email;

    @Crypto(type = CryptoType.AES256)
    private String phoneNumber;

    @Crypto(type = CryptoType.RSA)
    private String ci;

}
