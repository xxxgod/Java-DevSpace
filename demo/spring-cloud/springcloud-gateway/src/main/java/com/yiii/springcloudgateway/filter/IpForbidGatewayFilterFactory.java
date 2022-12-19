package com.yiii.springcloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 局部过滤，限制IP访问
 */

//@Component
public class IpForbidGatewayFilterFactory extends AbstractGatewayFilterFactory<IpForbidGatewayFilterFactory.Config> {

    private static final String PARAM_NAME = "forbidIp";

    public IpForbidGatewayFilterFactory() {
        super(Config.class);
    }

    // 这个方法指定属性名称
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARAM_NAME);
    }
    @Override
    public GatewayFilter apply(Config config) {
        // grab configuration from Config object
        return (exchange, chain) -> {
            // 获取请求参数
            String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
            System.out.println("ip地址：" + ip);
            if(config.getForbidIp().equals(ip)){
                // 放行
                return chain.filter(exchange);
            }
            // 不放行
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        };
    }

    //Put the configuration properties for your filter here
    public static class Config {
        private String forbidIp;
        public String getForbidIp(){
            return forbidIp;
   }
        public void setForbidIp(String forbidIp) {
            this.forbidIp = forbidIp;
        }
    }
}