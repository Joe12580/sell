package com.qpeng.sell.controller;

import com.qpeng.sell.dto.OrderDTO;
import com.qpeng.sell.enums.ResultEnum;
import com.qpeng.sell.exception.SellException;
import com.qpeng.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        ModelAndView model = new ModelAndView("order/list");
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        model.addObject("orderDTOPage", orderDTOPage);
        model.addObject("currentPage", page);
        model.addObject("size", size);
        return model;
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg", e.getMessage());
            errorModel.addObject("url", "/sell/seller/order/list");
            return errorModel;
       }
        ModelAndView model = new ModelAndView("common/success");
        model.addObject("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        model.addObject("url", "/sell/seller/order/list");
        return model;
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId){
        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg", e.getMessage());
            errorModel.addObject("url", "/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("order/detail");
        model.addObject("orderDTO", orderDTO);
        return model;
    }

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            ModelAndView errorModel = new ModelAndView();
            errorModel.setViewName("common/error");
            errorModel.addObject("msg", e.getMessage());
            errorModel.addObject("url", "/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("common/success");
        model.addObject("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        model.addObject("url", "/sell/seller/order/list");
        return model;
    }

}
