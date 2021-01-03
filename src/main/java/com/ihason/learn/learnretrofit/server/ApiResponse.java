package com.ihason.learn.learnretrofit.server;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * API 响应数据模板
 *
 * @author Hason
 */
@Getter
@Setter
@ToString
public class ApiResponse<T> {

    /** 错误码 */
    private Integer code;

    /** 错误消息 */
    private String message;

    /** 数据 */
    private T data;

    public static <T> ApiResponse<T> of(T data) {
        ApiResponse<T> response = ofEmpty();
        response.setData(data);
        return response;
    }

    private static <T> ApiResponse<T> ofEmpty() {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(0);
        response.setMessage("success");
        return response;
    }

}
