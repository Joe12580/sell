package com.qpeng.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qpeng.sell.enums.ProductStatusEnum;
import com.qpeng.sell.utils.EnumUtil;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class ProductInfo {

    @Id
    private String productId;

    /* 商品名 */
    private String productName;

    /* 商品售份 */
    private String productSell;

    /* 商品评价率值 */
    private String productRating;

    /* 商品单价 */
    private BigDecimal productPrice;

    /* 库存 */
    private Integer productStock;

    /* 商品描述 */
    private String productDescription;

    /* 商品小图 */
    private String productIcon;

    /* 商品状态,0正常1下架 */
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /* 类目编号 */
    private Integer categoryType;

    /* 创建时间 */
    private Date createTime;

    /* 修改时间 */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getEnumByCode(productStatus, ProductStatusEnum.class);
    }

}
