package com.example.lwallet;
import org.mindrot.jbcrypt.*;
public class HashB {
    private String bcryptHashedString;

    public String bcryptHash(String desiredString) {
        //BCrypt hash the desiredString
        //May modify from 10-12 (Hashing performance differs with different log_rounds)
        bcryptHashedString = BCrypt.hashpw(desiredString, BCrypt.gensalt(11));
        return bcryptHashedString;
    }
}