package com.suo.url.web.shorts;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Response<T> implements Serializable {
    /** 必填 是否成功标识 */
    private Boolean success;

    /** 非必填 返回结果集 */
    private T result;

    /** 失败时必填 业务码/失败码 全局需唯一 */
    private String code;

    /** 失败时必填 业务文案/失败文案提示 */
    private String message;

    public Boolean isSuccess() {
        return this.success;
    }

    public static <T> Response<T> ok(T data) {
        Response resp = new Response();
        resp.setResult(data);
        resp.setSuccess(true);
        return resp;
    }

    public static <T> Response<T> ok() {
        return ok((T)null);
    }

    public static <T> Response<T> fail(String code, String message) {
        Response resp = new Response();
        resp.setCode(code);
        resp.setMessage(message);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> Response<T> failOfMessage(String message) {
        Response resp = new Response();
        resp.setMessage(message);
        resp.setSuccess(false);
        return resp;
    }

    public static <T> Response<T> fail(String code) {
        Response resp = new Response();
        resp.setCode(code);
        resp.setSuccess(false);
        return resp;
    }
}
