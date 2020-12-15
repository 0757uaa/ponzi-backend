package com.uaa.ponzi.common.converter;

import com.baomidou.mybatisplus.annotation.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// 前端参数传递，转换成对应的枚举类型
@Component
public class EnumConverterFactory implements ConverterFactory<String, IEnum> {

    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> aClass) {
        return new StringToIEnum<T>(aClass);
    }


    private static class StringToIEnum<T extends IEnum> implements Converter<String, T> {

        private Class<T> targerType;

        public StringToIEnum(Class<T> targerType) {
            this.targerType = targerType;
        }


        @Override
        public T convert(String s) {
            if (StringUtils.isEmpty(s)) {
                return null;
            }

            return (T) EnumConverterFactory.getIEnum(this.targerType, s);
        }

    }

    public static <T extends IEnum> Object getIEnum(Class<T> targerType, String source) {
        for (T enumObj : targerType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getValue()))) {
                return enumObj;
            }
        }
        return null;
    }

}
