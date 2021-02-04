package com.ihason.learn.learnretrofit.client;

import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.LogStrategy;
import com.ihason.learn.learnretrofit.client.interceptor.SignInterceptor;
import com.ihason.learn.learnretrofit.server.ApiListResponse;
import com.ihason.learn.learnretrofit.server.ApiResponse;
import com.ihason.learn.learnretrofit.server.ResourceDTO;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.time.Duration;

/**
 * 基于 retrofit-spring-boot-starter 增强的 API
 * 资源 API 客户端
 *
 * @author Hason
 */
@RetrofitClient(baseUrl = "${retrofit.resource.baseUrl}", logStrategy = LogStrategy.BODY)
@Intercept(handler = SignInterceptor.class, include = "/**", exclude = "/login")
public interface EnhanceResourceApi {

    @OkHttpClientBuilder
    static OkHttpClient.Builder client() {
        // 自定义 client，必须返回 builder 类型
        return new OkHttpClient.Builder()
                .readTimeout(Duration.ofSeconds(10));
    }

    /**
     * 查询全部资源
     */
    @GET(value = "resources")
    Call<ApiListResponse<ResourceDTO>> listAll();

    /**
     * 查询全部资源
     */
    @GET(value = "resources")
    ApiListResponse<ResourceDTO> listAllToObject();

    /**
     * 查询全部资源（自动添加访问 key）
     */
    @GET(value = "resources")
    ApiListResponse<ResourceDTO> listAllWithAccessKey();

    /**
     * 获取 id 指定的资源
     *
     * @param id id
     */
    @GET(value = "resources/{id}")
    Call<ApiResponse<ResourceDTO>> get(@Path("id") Long id);

    /**
     * 保存资源
     *
     * @param dto 资源
     */
    @POST(value = "resources")
    Call<ApiResponse<ResourceDTO>> createCall(@Body ResourceDTO dto);

}
