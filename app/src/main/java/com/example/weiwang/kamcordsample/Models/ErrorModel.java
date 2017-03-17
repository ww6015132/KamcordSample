package com.example.weiwang.kamcordsample.Models;

import java.util.StringTokenizer;

/**
 * Created by weiwang on 3/15/17.
 */

public class ErrorModel {
    String code;
    String message;
    String rootCause;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }
}
