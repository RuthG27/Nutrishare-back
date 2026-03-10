package com.nutrishare.backend.util.scripts;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "Ruth123";
        String hashedPassword = encoder.encode(password);
        System.out.println("Contraseña original: " + password);
        System.out.println("Hash BCrypt: " + hashedPassword);
    }
}
