package com.mpolder.jarche.request.user;

import com.mpolder.jarche.interfaces.IRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientCoupleUserRequest implements IRequest {
    private String code;

    public ClientCoupleUserRequest() {
    }

    public ClientCoupleUserRequest(String code) {
        this.code = code;
    }

    public String getCode() { return code; }

    @Override
    public boolean validate() {
        boolean valid = this.code != null && !this.code.equals("");
        Pattern pattern = Pattern.compile("(\\d{4})");
        Matcher m = pattern.matcher(this.code);
        valid &= m.matches();
        return valid;
    }
}
