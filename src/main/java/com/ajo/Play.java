package com.ajo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Play {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("password"));
    }
}
