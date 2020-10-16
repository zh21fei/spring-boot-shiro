package com.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 功能描述
 *
 * @author zhangpengfei
 * @since 2020/6/2
 */
@SpringBootApplication
@ComponentScan("com.neo.*")
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableScheduling //开启定时任务支持
public class ShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroApplication.class, args);
	}
}
