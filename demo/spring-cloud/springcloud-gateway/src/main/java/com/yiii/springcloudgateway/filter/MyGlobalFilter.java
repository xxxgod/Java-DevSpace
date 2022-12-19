package com.yiii.springcloudgateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//全局过滤
//@Component
public class MyGlobalFilter implements GlobalFilter,Ordered {
    /**
     * @author 栗子
     * @Description 判断请求参数是否有token值
     * @Date 17:33 2019/7/16
     * @param exchange
     * @param chain
     * @return reactor.core.publisher.Mono<java.lang.Void>
     **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求参数token
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 如果为空，则无法继续执行业务
        if (StringUtils.isEmpty(token)) {
            System.out.println("token is empty!!!");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED); // 401：无效认证
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
    /**
     * @author 栗子
     * @Description 代表过滤器的优先级，值越小优先级越高（先执行）
     * @Date 17:32 2019/7/16
     * @param
     * @return int
     **/
    @Override
    public int getOrder() {
        return 0;
    }
}