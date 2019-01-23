package com.qpeng.sell.service;

import com.qpeng.sell.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);

}
