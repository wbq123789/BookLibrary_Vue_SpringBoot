/*
 * Copyright (c) wbq 2023.
 */

package booklibrary.library_backend.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: RabbitMq配置类
 * @Author: 王贝强
 * @Date: 2023/12/26
 */
@Configuration
public class RabbitConfiguration {
    @Bean("mailQueue")
    public Queue queue() {
        return QueueBuilder
                .durable("mail")
                .build();
    }

    /**
     * @Description: 配置MessageJSON序列化方式
     * @Param: null
     * @return: org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
     * @Author: 王贝强
     * @Date: 2023/12/26
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}