package me.gaegul.crypto;

import org.springframework.security.crypto.bcrypt.BCrypt;

public enum CryptoType {
    BCRYPT {
        @Override
        public String encrypt(String plain) {
            return BCrypt.hashpw(plain, BCrypt.gensalt());
        }
    }, RSA {
        @Override
        public String encrypt(String plain) {
            return RSAUtil.encrypt(plain);
        }
    }, AES256 {
        @Override
        public String encrypt(String plain) {
            return AES256Util.encrypt(plain);
        }
    };

    public abstract String encrypt(String plain);
}
