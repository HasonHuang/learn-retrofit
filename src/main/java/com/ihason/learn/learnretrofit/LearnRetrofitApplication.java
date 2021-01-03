package com.ihason.learn.learnretrofit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用步骤：
 * <pre>
 * 1、启动本程序，提供 Server 端服务接口
 * 2、使用单元测试类进行客户端调用
 * </pre>
 *
 * @author Hason
 */
@SpringBootApplication
public class LearnRetrofitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnRetrofitApplication.class, args);
	}

}
