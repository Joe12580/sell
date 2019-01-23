package com.qpeng.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qpeng.sell.dto.OrderDTO;
import com.qpeng.sell.entity.OrderDetail;
import com.qpeng.sell.enums.ResultEnum;
import com.qpeng.sell.exception.SellException;
import com.qpeng.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();

        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
