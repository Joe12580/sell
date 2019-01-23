package com.qpeng.sell.controller;

import com.qpeng.sell.VO.ProductCategoryVO;
import com.qpeng.sell.VO.ProductInfoVO;
import com.qpeng.sell.VO.ResultVO;
import com.qpeng.sell.entity.ProductCategory;
import com.qpeng.sell.entity.ProductInfo;
import com.qpeng.sell.service.ProductCategoryService;
import com.qpeng.sell.service.ProductInfoService;
import com.qpeng.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/list")
    public ResultVO list(){
        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        // 2.根据商品列表构建一个商品类别编号的集合
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        // 3.根据商品类别编号集合查询对应的类别
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        // 4.数据拼装
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();// 将原数据集合转化成VO集合
        for (ProductCategory productCategory : productCategoryList) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();//构建VO对象
            BeanUtils.copyProperties(productCategory, productCategoryVO); //将原对象的数据赋值到VO对象中,Spring提供一个叫做BeanUtils的类
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //将商品原数据转化成商品VO集合
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO); //将商品VO集合设置到类别对象中
                }
            }
            productCategoryVO.setProductInfoVOList(productInfoVOList); //将转化好的商品VO集合设置到类别中
            productCategoryVOList.add(productCategoryVO);  //将VO对象放到集合中
        }
        return ResultVOUtils.success(productCategoryVOList);
    }

}
