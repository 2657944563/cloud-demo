package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    //使用spring提供的http请求工具
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    //使用Feign发送的远程调用
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.发送请求
        User user = userClient.findById(order.getUserId());
        //3.封装user到order数据中
        order.setUser(user);
        // 4.返回
        return order;
    }

    //使用RestTemplate发送的远程调用
//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.组合请求路径
////        String url = "http://localhost:8081/user/"+order.getUserId();
//        String url = "http://userservice/user/"+order.getUserId();
//        //2.1发送请求获取数据
//        User user = restTemplate.getForObject(url, User.class);
//        //3.封装user到order数据中
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
}
