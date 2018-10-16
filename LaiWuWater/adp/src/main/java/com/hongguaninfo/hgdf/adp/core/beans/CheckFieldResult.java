package com.hongguaninfo.hgdf.adp.core.beans;

import java.io.Serializable;

public class CheckFieldResult implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7837832710469761578L;

    private String field;
    private String message;

    public CheckFieldResult(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
