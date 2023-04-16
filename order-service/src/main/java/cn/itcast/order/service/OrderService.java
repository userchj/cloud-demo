package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;

import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //url路径
        //String url ="http://userservice/user/"+order.getUserId();
        //查询
        User user=userClient.getOrderById(order.getUserId());

        order.setUser(user);
        // 4.返回

        return order;
    }

//    @Autowired
//    private RestTemplate restTemplate;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //url路径
//        String url ="http://userservice/user/"+order.getUserId();
//        //查询
//        User user=restTemplate.getForObject(url, User.class);
//
//        order.setUser(user);
//        // 4.返回
//
//        return order;
//    }
}
