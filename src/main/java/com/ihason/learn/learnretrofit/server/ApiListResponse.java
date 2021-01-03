package com.ihason.learn.learnretrofit.server;

import java.util.Collection;
import java.util.Collections;

/**
 * API 响应列表数据模板
 *
 * @author Hason
 */
public class ApiListResponse<T> extends ApiResponse<Collection<T>> {

    public static <T> ApiListResponse<T> of(Collection<T> data) {
        ApiListResponse<T> response = ofEmpty();
        response.setData(data);
        return response;
    }

    public static <T> ApiListResponse<T> ofSingle(T data) {
        ApiListResponse<T> response = ofEmpty();
        response.setData(Collections.singletonList(data));
        return response;
    }

    private static <T> ApiListResponse<T> ofEmpty() {
        ApiListResponse<T> response = new ApiListResponse<>();
        response.setCode(0);
        response.setMessage("success");
        return response;
    }

}
