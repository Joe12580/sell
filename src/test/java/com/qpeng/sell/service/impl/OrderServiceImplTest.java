package com.qpeng.sell.service.impl;

import com.qpeng.sell.dto.OrderDTO;
import com.qpeng.sell.entity.OrderDetail;
import com.qpeng.sell.enums.OrderStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void createTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张一山");
        orderDTO.setBuyerPhone("15135120000");
        orderDTO.setBuyerAddress("天津市东丽区建设路12号");
        orderDTO.setBuyerOpenid("123");
        List<OrderDetail> list = new ArrayList<>();
        list.add(new OrderDetail("1548061703967928568",10));
        list.add(new OrderDetail("1548061703981912975",2));
        orderDTO.setOrderDetailList(list);
        orderService.create(orderDTO);
    }

    @Test
    public void findOneTest(){
        OrderDTO orderDTO = orderService.findOne("1548061703957537790");
        System.out.println(orderDTO);
    }

    @Test
    public void findListTest(){
        Page<OrderDTO> page = orderService.findList("123",new PageRequest(0, 2));
        for (OrderDTO orderDTO : page.getContent()) {
            System.out.println(orderDTO);
        }
    }

    @Test
    public void cancelTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1548061703957537790");
        List<OrderDetail> list = new ArrayList<>();
        list.add(new OrderDetail("1548061703967928568",10));
        list.add(new OrderDetail("1548061703981912975",2));
        orderDTO.setOrderDetailList(list);
        orderService.cancel(orderDTO);
    }

    @Test
    public void finishTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1548061703957537790");
        orderService.finish(orderDTO);
    }

    @Test
    public void paidTest(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1548061703957537790");
        orderService.paid(orderDTO);
    }

}