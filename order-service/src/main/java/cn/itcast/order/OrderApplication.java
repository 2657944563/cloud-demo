package cn.itcast.order;

import cn.itcast.feign.clients.UserClient;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import feign.Feign;
import org.apache.tomcat.util.digester.Rule;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableFeignClients(clients = {UserClient.class})
@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    @LoadBalanced//负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    //设置负载均衡策略
//    public IRule rule() {
//        return new RandomRule();
//    }
}