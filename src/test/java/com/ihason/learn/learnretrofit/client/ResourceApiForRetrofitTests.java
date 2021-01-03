package com.ihason.learn.learnretrofit.client;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.ihason.learn.learnretrofit.server.ApiListResponse;
import com.ihason.learn.learnretrofit.server.ApiResponse;
import com.ihason.learn.learnretrofit.server.ResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Retrofit 原生 API 单元测试用例
 *
 * @author Hason
 */
@Slf4j
public class ResourceApiForRetrofitTests {

    private String host = "http://localhost:8080";

    private ResourceApi api;

    @BeforeEach
    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        api = retrofit.create(ResourceApi.class);
    }

    @Test
    public void should_listAll_to_object() {
        ApiListResponse<ResourceDTO> object = api.listAllToObject();
        log.debug("object : {}", object);
        assertThat(object).isNotNull();
    }

    @Test
    public void should_listAll() throws IOException {
        Call<ApiListResponse<ResourceDTO>> call = api.listAll();
        Response<ApiListResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void should_listAll_with_filter_id() throws IOException {
        Call<ApiListResponse<ResourceDTO>> call = api.listAll(1L);
        Response<ApiListResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void should_listAll_with_filter_style() throws IOException {
        Call<ApiListResponse<ResourceDTO>> call = api.listAllWithFilter("abc");
        Response<ApiListResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void should_get_one() throws IOException {
        Call<ApiResponse<ResourceDTO>> call = api.get(1L);
        Response<ApiResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void should_get_twice_with_clone() throws IOException {
        Call<ApiResponse<ResourceDTO>> call = api.get(1L);
        Response<ApiResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();

        Response<ApiResponse<ResourceDTO>> response2 = call.clone().execute();
        printResponse(response2);
        assertThat(response2.isSuccessful()).isTrue();

    }

    @Test
    public void should_get_with_fixed_header() throws IOException {
        Call<ApiResponse<ResourceDTO>> call = api.getWithFixedHeader(1L);
        Response<ApiResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void should_get_with_server_error() throws IOException {
        Call<ApiResponse<ResourceDTO>> call = api.get(-1L);
        Response<ApiResponse<ResourceDTO>> response = call.execute();
        printResponse(response);
        assertThat(response.isSuccessful()).isFalse();
    }

    @Test
    public void should_get_with_deserializer_object_error() throws IOException {
        Call<ApiListResponse<ResourceDTO>> call = api.getErrorForDeserializeWrongObject(1L);
        AbstractThrowableAssert<?, ? extends Throwable> anAssert = assertThatThrownBy(call::execute);
        anAssert.isExactlyInstanceOf(MismatchedInputException.class);
    }

    public static void printResponse(Response<?> response) throws IOException {
        log.debug("isSuccessful : {}", response.isSuccessful());
        log.debug("Code : {}", response.code());
        log.debug("Body : {}", response.body());
        log.debug("ErrorBody : {}", response.errorBody());
        if (!response.isSuccessful() && response.errorBody() != null) {
            log.debug("ErrorBody : {}", response.errorBody().string());
        }
        log.debug("response : {}", response.headers());
        log.debug("Raw response : {}", response.raw());
    }

}
