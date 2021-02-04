package com.ihason.learn.learnretrofit.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 资源 API
 *
 * @author Hason
 */
@Slf4j
@RestController
public class ResourceController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/resources")
    public ApiListResponse<ResourceDTO> listAll(@RequestParam(name = "id", required = false) Long id) {
        printHeaders();
        return id != null ? ApiListResponse.ofSingle(ResourceRepository.DB.get(id)) : ApiListResponse.of(ResourceRepository.DB.values());
    }

    @GetMapping("/resources/{id}")
    public ApiResponse<ResourceDTO> get(@PathVariable("id") Long id) {
        printHeaders();
        validateId(id);
        return ApiResponse.of(ResourceRepository.DB.get(id));
    }

    @PostMapping("/resources")
    public ApiResponse<ResourceDTO> save(@RequestBody ResourceDTO dto) {
        log.debug("body: {}", dto);
        printHeaders();

        Long id = dto.getId();
        if (id != null) {
            validateId(dto.getId());
        } else {
            id = Math.abs(ThreadLocalRandom.current().nextLong());
        }

        dto.setId(id);
        ResourceRepository.DB.put(id, dto);
        return ApiResponse.of(dto);
    }

    private void printHeaders() {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String next = headerNames.nextElement();
            log.debug("Header --> {} : {}", next, request.getHeader(next));
        }
    }

    private void validateId(Long id) {
        if (id < 0) {
            // 模拟异常
            throw new RuntimeException("id不能小于0");
        }
    }

}
