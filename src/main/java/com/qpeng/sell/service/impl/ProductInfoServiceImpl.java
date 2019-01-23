package com.qpeng.sell.service.impl;

import com.qpeng.sell.dto.CartDTO;
import com.qpeng.sell.entity.ProductInfo;
import com.qpeng.sell.enums.ProductStatusEnum;
import com.qpeng.sell.enums.ResultEnum;
import com.qpeng.sell.exception.SellException;
import com.qpeng.sell.repository.ProductInfoRepository;
import com.qpeng.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo findOne(String productInfoId) {
        return productInfoRepository.findOne(productInfoId);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            // 判断商品是否存在
            if (productInfo == null) {
                //当商品不存在时抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 判断数量是否合法
            if(cartDTO.getProductQuantity() <= 0){
                throw new SellException(ResultEnum.QUANTITY_NOT_LEGAL);
            }

            // 判断库存是否足够
            if(cartDTO.getProductQuantity() > productInfo.getProductStock()) {
                throw new SellException(ResultEnum.STOCK_NOT_ENOUGH);
            }

            // 扣库存
            productInfo.setProductStock(productInfo.getProductStock() - cartDTO.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findOne(cartDTO.getProductId());
            // 判断商品是否存在
            if (productInfo == null) {
                //当商品不存在时抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            // 判断数量是否合法
            if(cartDTO.getProductQuantity() <= 0){
                throw new SellException(ResultEnum.QUANTITY_NOT_LEGAL);
            }

            // 加库存
            productInfo.setProductStock(productInfo.getProductStock() + cartDTO.getProductQuantity());
            productInfoRepository.save(productInfo);
        }
    }
}
