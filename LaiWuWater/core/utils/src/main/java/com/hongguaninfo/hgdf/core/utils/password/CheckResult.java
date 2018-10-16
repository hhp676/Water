package com.hongguaninfo.hgdf.core.utils.password;

import java.io.Serializable;

public class CheckResult implements Serializable {
    private boolean valid;
    private String msg;

    public CheckResult(boolean valid, String msg) {
        this.valid = valid;
        this.msg = msg;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMsg() {
        return msg;
    }
}