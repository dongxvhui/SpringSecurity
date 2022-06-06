package com.geek;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class HttpDigestApplicationTests {

    /**
     * 摘要的生成密码
     * @throws NoSuchAlgorithmException
     */
    @Test
    void contextLoads() throws NoSuchAlgorithmException {
        String rawPassword = "geek:myrealm:123";
        MessageDigest digest = MessageDigest.getInstance("MD5");
        String s = new String(Hex.encode(digest.digest(rawPassword.getBytes())));
        System.out.println(s);
    }

}
