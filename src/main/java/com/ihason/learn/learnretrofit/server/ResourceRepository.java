package com.ihason.learn.learnretrofit.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 模拟资源库
 *
 * @author Hason
 */
public class ResourceRepository {

    public static final Map<Long, ResourceDTO> DB = new ConcurrentHashMap<>();

    static {
        // 模拟默认数据
        initData();
    }

    private static void initData() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ResourceDTO r1 = ResourceDTO.of(random.nextLong(), "Cat");
        DB.put(r1.getId(), r1);
        ResourceDTO r2 = ResourceDTO.of(random.nextLong(), "Dog");
        DB.put(r2.getId(), r2);
        ResourceDTO r3 = ResourceDTO.of(1L, "Shiba");
        DB.put(r3.getId(), r3);
    }

}
