package com.anton.gs.model.util.impl;

import com.anton.gs.model.util.PasswordCoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class PasswordCoderImpl implements PasswordCoder {
    private static PasswordCoderImpl passwordCoder;
    private static final String HASHING_SYSTEM = "SHA-1";
    private static final String CHARACTER_ENCODING = "UTF-8";

    private PasswordCoderImpl() {

    }

    public static PasswordCoderImpl getInstance() {
        if (passwordCoder == null) {
            passwordCoder = new PasswordCoderImpl();
        }
        return passwordCoder;
    }

    @Override
    public String codePassword(String password) {
        MessageDigest messageDigest = null;
        String passwordEncrypted = null;
        byte[] buffer = null;
        try {
            messageDigest = MessageDigest.getInstance(HASHING_SYSTEM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            messageDigest.update(password.getBytes(CHARACTER_ENCODING));
            buffer = messageDigest.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BigInteger integer = new BigInteger(1, buffer);
        passwordEncrypted = integer.toString(16);
        return passwordEncrypted;
    }
}
