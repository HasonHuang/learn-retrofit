package com.ihason.learn.learnretrofit.client.interceptor;

import com.github.lianjiatech.retrofit.spring.boot.interceptor.BaseGlobalInterceptor;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 全局拦截器：设置 header、host
 *
 * @author Hason
 */
@Component
public class GlobalInterceptor extends BaseGlobalInterceptor {

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
//        HttpUrl newUrl = request.url()
//                .newBuilder()
//                .scheme("http")
//                .host("localhost")
//                .port(8080)
//                .build();
        Request newRequest = request.newBuilder()
                .addHeader("Global-Header", "Global-Header-Value")
//                .url(newUrl)
                .build();
        return chain.proceed(newRequest);
    }

}
