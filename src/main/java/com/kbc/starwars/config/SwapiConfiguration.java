package com.kbc.starwars.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class SwapiConfiguration {

    @Bean
    public UserAgentInterceptor authFeign() {
        return new UserAgentInterceptor();
    }

    private class UserAgentInterceptor implements RequestInterceptor {
        @Override
        public void apply(RequestTemplate template) {
            template.header("User-Agent", "curl/7.45.0");

        }
    }
}
