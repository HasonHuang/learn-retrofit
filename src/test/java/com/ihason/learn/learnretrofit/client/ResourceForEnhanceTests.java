package com.ihason.learn.learnretrofit.client;

import com.ihason.learn.learnretrofit.server.ApiListResponse;
import com.ihason.learn.learnretrofit.server.ApiResponse;
import com.ihason.learn.learnretrofit.server.ResourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static com.ihason.learn.learnretrofit.client.ResourceApiForRetrofitTests.printResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * retrofit-spring-boot-starter 测试用例
 *
 * @author Hason
 */
@Slf4j
@SpringBootTest(properties = {
        "retrofit.resource.baseUrl=http://localhost:8080",
        "retrofit.log.enable=true",
        "retrofit.retry.enable-global-retry=false",
})
public class ResourceForEnhanceTests {

    @Autowired
    private EnhanceResourceApi api;

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
    public void should_listAll_with_access_key() {
        ApiListResponse<ResourceDTO> call = api.listAllWithAccessKey();
        assertThat(call).isNotNull();
    }

    @Test
    public void should_get_with_server_error() {
        Call<ApiResponse<ResourceDTO>> call = api.get(-1L);
        AbstractThrowableAssert<?, ? extends Throwable> anAssert = assertThatThrownBy(call::execute);
        anAssert.isExactlyInstanceOf(com.github.lianjiatech.retrofit.spring.boot.exception.RetrofitException.class);
    }

}
