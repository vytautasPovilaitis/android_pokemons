package com.example.vytautas.myapplication;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vytautas on 02/02/2018.
 */

public class Validation {

    private static final String VALID_CREDENTIALS_REGEX = "^[A-Za-z0-9.-]{5,13}$";
    private static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    private static final String VALID_DOUBLE_REGEX = "^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$";

    public static boolean isValidCredentials(String credentials) {
        Pattern credentialsPattern = Pattern.compile(VALID_CREDENTIALS_REGEX);
        Matcher credentialsMatcher = credentialsPattern.matcher(credentials);
        return credentialsMatcher.find();
    }

    public static boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    public static boolean isValidDouble(String name) {
        Pattern namePattern = Pattern.compile(VALID_DOUBLE_REGEX);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.find();
    }


}


