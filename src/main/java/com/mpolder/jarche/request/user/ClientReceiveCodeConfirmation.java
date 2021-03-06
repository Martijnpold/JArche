package com.mpolder.jarche.request.user;

import com.mpolder.jarche.interfaces.IConfirmation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientReceiveCodeConfirmation implements IConfirmation {
    private String code;

    public ClientReceiveCodeConfirmation() {
    }

    public ClientReceiveCodeConfirmation(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean validate() {
        boolean valid = this.code != null;
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher m = pattern.matcher(this.code);
        valid &= m.matches();
        return valid;
    }
}
