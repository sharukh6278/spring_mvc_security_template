package org.spring.security.model;

import java.util.Date;

public class FridayJWTToken {
    private String accessToken;

    private Date tokenCreationTime;

    private Date tokenExpireTime;

    private String tokenType="Bearer";

    public FridayJWTToken(String accessToken, Date tokenCreationTime, Date tokenExpireTime, String tokenType) {
        this.accessToken = accessToken;
        this.tokenCreationTime = tokenCreationTime;
        this.tokenExpireTime = tokenExpireTime;
        this.tokenType = tokenType;
    }

    public FridayJWTToken(){}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(Date tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }

    public Date getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Date tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }



    @Override
    public String toString() {
        return "FridayJWTToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenCreationTime=" + tokenCreationTime +
                ", tokenExpireTime=" + tokenExpireTime +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }
}
