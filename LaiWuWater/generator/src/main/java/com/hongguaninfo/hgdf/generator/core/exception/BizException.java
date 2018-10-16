package com.hongguaninfo.hgdf.generator.core.exception;

import com.hongguaninfo.hgdf.core.utils.exception.BaseException;

public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}