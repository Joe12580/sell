package com.qpeng.sell.repository;

import com.qpeng.sell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    private static final String OPENID = "123";

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1548061703957537790");
        orderMaster.setBuyerAddress("天津市东丽区建设路12号");
        orderMaster.setBuyerName("张一山");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("15236548858");
        orderMaster.setOrderAmount(new BigDecimal(82));
        repository.save(orderMaster);
    }

    @Test
    public void findByBuyerOpenidTest(){
        Page<OrderMaster> page = repository.findByBuyerOpenid(OPENID,new PageRequest(0,2));
        for (OrderMaster orderMaster : page.getContent()) {
            System.out.println(orderMaster);
        }
    }

}