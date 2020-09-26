package ch.fronis.admin.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    @Test
    public void run() {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        String rawPassword = "alcopipsleoni";
        String encodedPassword = enc.encode(rawPassword);
        System.out.println(encodedPassword);
        BCryptPasswordEncoder enc2 = new BCryptPasswordEncoder();
        Assert.assertTrue(enc2.matches(rawPassword, encodedPassword));
    }
}
