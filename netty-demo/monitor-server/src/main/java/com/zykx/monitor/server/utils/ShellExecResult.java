package com.zykx.monitor.server.utils;

import java.io.Serializable;

/**
 * 远程执行shell的结果
 */
public class ShellExecResult implements Serializable {
    private static final long serialVersionUID = -3591555670360983229L;

    private int code;       // 结果码
    private String message; // 结果信息

    public ShellExecResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ShellExecResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
