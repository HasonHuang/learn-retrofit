package com.ihason.learn.learnretrofit.client.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 模拟接口 token 的拦截器
 *
 * @author Hason
 */
@Component
public class SignInterceptor extends BasePathMatchInterceptor {

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder()
                // 简单模拟密钥
                .addHeader("Access-Key", "Access-Key-Value")
                .addHeader("Secret-Key", "Secret-Key-Value")
                .build();
        return chain.proceed(newRequest);
    }

}
