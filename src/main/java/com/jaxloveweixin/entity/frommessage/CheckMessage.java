package com.jaxloveweixin.entity.frommessage;

import com.google.gson.Gson;
import org.springframework.boot.json.GsonJsonParser;

/**
 * @author wangdejun
 * @description: TODO description
 * @date 2019/11/16 15:27
 */
public class CheckMessage {

    private String signature;

    private String timestamp;

    private String nonce;

    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    @Override
    public String toString() {
        return "CheckMessage：" + new Gson().toJson(this);
    }
}
