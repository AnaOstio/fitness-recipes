package com.empathy.restapi.configuration;



import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@EnableAutoConfiguration(exclude = SpringApplicationAdminJmxAutoConfiguration.class)
public class ElasticSearchConfiguration extends ElasticsearchConfiguration {


    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withSocketTimeout(999999999)
                .build();
    }
}
