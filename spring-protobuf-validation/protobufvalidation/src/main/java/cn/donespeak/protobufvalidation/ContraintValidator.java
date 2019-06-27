package cn.donespeak.protobufvalidation;

/**
 *
 * @author Serious
 * @date 2017/6/28
 */
public interface ContraintValidator {

    void validate(Class<?> fieldClass, String fieldName, Object fieldValue,  
        Object extensionValue) throws IllegalArgumentException;
}
