package com.hongguaninfo.hgdf.adp.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordTokenSignToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer tokenId;
    private String tokenSign;
    
    public String getTokenSign() {
        return tokenSign;
    }

    public void setTokenSign(String tokenSign) {
        this.tokenSign = tokenSign;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public UsernamePasswordTokenSignToken() {
        super();
    }

    public UsernamePasswordTokenSignToken(String username, char[] password,
            boolean rememberMe, String host, String tokenSign) {
        super(username, password, rememberMe, host);
        this.tokenSign = tokenSign;
    }

}