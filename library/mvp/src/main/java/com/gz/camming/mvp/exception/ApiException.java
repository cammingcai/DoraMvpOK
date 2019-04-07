package com.gz.camming.mvp.exception;

/**
 * Created by camming on 2019/4/7.
 *
 */

public class ApiException extends Exception {

    private int code;
    private String message;

    public ApiException(int code ,String message){
        this.code = code;
        this.message =  message;
    }

    public ApiException(int code ,String message,String defaultMessage){
        super(defaultMessage);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
