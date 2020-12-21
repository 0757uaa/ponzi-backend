package com.uaa.ponzi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <!--./start 项目打包成war形式，需要经过四步，第【4】步-->
 */
public class ApplicationWarStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个SpringBoot启动类
        return builder.sources(Application.class);
    }
}
