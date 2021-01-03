package com.ihason.learn.learnretrofit.client;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.ihason.learn.learnretrofit.server.ApiListResponse;
import com.ihason.learn.learnretrofit.server.ApiResponse;
import com.ihason.learn.learnretrofit.server.ResourceDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * （Retrofit 原生 API）
 * 资源 API 客户端
 *
 * @author Hason
 */
@RetrofitClient
public interface ResourceApi {

    /**
     * 查询全部资源（直接返回对象，不使用 Call）
     */
    @GET(value = "resources")
    ApiListResponse<ResourceDTO> listAllToObject();

    /**
     * 查询全部资源
     */
    @GET(value = "resources")
    Call<ApiListResponse<ResourceDTO>> listAll();

    /**
     * 查询全部资源，使用 id 过滤结果
     *
     * @param id 过滤参数
     */
    @GET(value = "resources")
    Call<ApiListResponse<ResourceDTO>> listAll(@Query("id") Long id);

    /**
     * 查询全部资源
     *
     * @param aName 测试客户端实际参数名的风格，没有其他意义
     */
    @GET(value = "resources")
    Call<ApiListResponse<ResourceDTO>> listAllWithFilter(@Query("a_name") String aName);

    /**
     * 获取 id 指定的资源
     *
     * @param id id
     */
    @GET(value = "resources/{id}")
    Call<ApiResponse<ResourceDTO>> get(@Path("id") Long id);

    /**
     * 固定 header 查询
     *
     * @param id id
     */
    @Headers("FixedHeader: 10086")
    @GET(value = "resources/{id}")
    Call<ApiResponse<ResourceDTO>> getWithFixedHeader(@Path("id") Long id);

    /**
     * （模拟反序列化错误：响应对象错误）获取 id 指定的资源
     *
     * @param id id
     */
    @GET(value = "resources/{id}")
    Call<ApiListResponse<ResourceDTO>> getErrorForDeserializeWrongObject(@Path("id") Long id);

}
