package cn.itcast.GateWay;

import lombok.val;
import org.apache.http.HttpStatus;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)//过滤优先级，越低优先级越高,Ordered同样作用
@Component
public class AuthorizeFilter implements GlobalFilter , Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
        String auth=queryParams.getFirst("authorization");
        if("admin".equals(auth)){
            return chain.filter(exchange);
        }
        exchange.getResponse().setRawStatusCode(HttpStatus.SC_UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
