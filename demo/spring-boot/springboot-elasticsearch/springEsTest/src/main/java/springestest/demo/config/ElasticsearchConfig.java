package springestest.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;

@Configuration
public class ElasticsearchConfig {

    @Bean
    TransportClientFactoryBean client() {
        TransportClientFactoryBean bean=new TransportClientFactoryBean();
        bean.setClusterNodes("localhost:9300");
        return bean;
    }




}

