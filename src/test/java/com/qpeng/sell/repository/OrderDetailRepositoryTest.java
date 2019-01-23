package com.qpeng.sell.repository;

import com.qpeng.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1548061703967928568");
        orderDetail.setOrderId("1548061703957537790");
        orderDetail.setProductIcon("aaa");
        orderDetail.setProductId("1003");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(8));
        orderDetail.setProductQuantity(82);
        repository.save(orderDetail);
    }

    @Test
    public void findByOrderIdTest(){
        for (OrderDetail orderDetail : repository.findByOrderId("1548061703957537790")) {
            System.out.println(orderDetail);
        }
    }

}