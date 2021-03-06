package com.qpeng.sell.service;

import com.qpeng.sell.dto.CartDTO;
import com.qpeng.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    /**查询所有在架商品*/
    List<ProductInfo> findUpAll();

    /**查询所有商品包含分页*/
    Page<ProductInfo> findAll(Pageable pageable);

    /**根据id查询商品*/
    ProductInfo findOne(String productInfoId);

    /**增加/修改*/
    ProductInfo save(ProductInfo productInfo);

    /**扣库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

    /**增加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

}
