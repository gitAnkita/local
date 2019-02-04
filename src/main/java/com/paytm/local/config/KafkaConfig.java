package com.paytm.local.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties("kafka")
@Data
public class KafkaConfig {

    private String bootstrapServers;

    private String topic;

    private String consumerId;

    private Boolean autocommit;

}
