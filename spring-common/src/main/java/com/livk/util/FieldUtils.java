package com.livk.util;

import com.livk.function.FieldFunction;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.FileNotFoundException;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * <p>
 * FieldUtils
 * </p>
 *
 * @author livk
 * @date 2022/9/28
 */
@Slf4j
@UtilityClass
public class FieldUtils extends org.apache.commons.lang3.reflect.FieldUtils {

    public <T> String getFieldName(FieldFunction<T> function) {
        try {
            Method method = function.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(function);
            String getter = serializedLambda.getImplMethodName();
            if (getter.startsWith("get")) {
                getter = getter.substring(3);
            } else if (getter.startsWith("is")) {
                getter = getter.substring(2);
            } else {
                throw new FileNotFoundException("缺少get|is方法");
            }
            if (StringUtils.hasText(getter)) {
                char[] cs = getter.toCharArray();
                cs[0] += 32;
                return String.valueOf(cs);
            }
            throw new FileNotFoundException("属性字段值丢失");
        } catch (Exception e) {
            log.error("获取字段名称失败 message: {}", e.getMessage(), e);
            return null;
        }
    }
}
