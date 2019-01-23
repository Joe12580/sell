package com.qpeng.sell.utils;

import com.qpeng.sell.enums.CodeEnum;
import com.qpeng.sell.enums.OrderStatusEnum;

public class EnumUtil {

    // 通过code值获得枚举对象
    public static <T extends CodeEnum> T getEnumByCode(Integer code, Class<T> EnumClass){
        for (T each : EnumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

}
