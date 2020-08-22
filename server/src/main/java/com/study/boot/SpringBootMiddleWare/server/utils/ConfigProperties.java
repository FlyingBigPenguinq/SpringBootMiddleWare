package com.study.boot.SpringBootMiddleWare.server.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @ClassName ConfigProperties
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/20
 * @Version V1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "wx.auth.token")
public class ConfigProperties implements Serializable {
    private String appid;
    private String appSecrect;
    private String appRandom;
    private String appVersion;

    public ConfigProperties() {
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppSecrect() {
        return appSecrect;
    }

    public void setAppSecrect(String appSecrect) {
        this.appSecrect = appSecrect;
    }

    public String getAppRandom() {
        return appRandom;
    }

    public void setAppRandom(String appRandom) {
        this.appRandom = appRandom;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
