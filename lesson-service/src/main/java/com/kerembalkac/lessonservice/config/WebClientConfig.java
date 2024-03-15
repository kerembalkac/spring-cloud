package com.kerembalkac.lessonservice.config;

import com.kerembalkac.lessonservice.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient studentWebClient(){
        return WebClient.builder()
                .baseUrl("http://student-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public StudentClient studentClient(){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(studentWebClient())).build();

        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
}
