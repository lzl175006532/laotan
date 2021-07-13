package com.laotan.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 外部容器方式启动配置
 * @author 李子龙
 */
public class ServletInitializer extends SpringBootServletInitializer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("外部容器方式启动");
		return application.sources(NetApplication.class);
	}

}
