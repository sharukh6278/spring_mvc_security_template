package org.spring.security.beans;

import javax.persistence.*;
import java.util.Date;


@Table(name = "Watch_Earn_JWTToken")
@Entity
public class JWTToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jwtTokenId;

    private String accessToken;

    private Date tokenCreationTime;

    private Date tokenExpireTime;

    private String tokenType="Bearer";

    private String email;

    public JWTToken(){}

    public JWTToken(long jwtTokenId, String accessToken, Date tokenCreationTime, Date tokenExpireTime, String tokenType, String email) {
        this.jwtTokenId = jwtTokenId;
        this.accessToken = accessToken;
        this.tokenCreationTime = tokenCreationTime;
        this.tokenExpireTime = tokenExpireTime;
        this.tokenType = tokenType;
        this.email = email;
    }

    @Override
    public String toString() {
        return "JWTToken{" +
                ", jwtTokenId='" + jwtTokenId + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", tokenCreationTime=" + tokenCreationTime +
                ", tokenExpireTime=" + tokenExpireTime +
                ", tokenType='" + tokenType + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public long getJwtTokenId() {
        return jwtTokenId;
    }

    public void setJwtTokenId(long jwtTokenId) {
        this.jwtTokenId = jwtTokenId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
