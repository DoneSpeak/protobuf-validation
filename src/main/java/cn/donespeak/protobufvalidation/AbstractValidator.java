package cn.donespeak.protobufvalidation;


/**
 * @author Serious
 * @date 2017/6/28
 */
public abstract class AbstractValidator implements ContraintValidator {

    @Override
    public void validate(Class<?> fieldClass, String fieldName, Object fieldValue,  
        Object extensionValue) throws IllegalArgumentException {
        String errInfo = String.format("validate error fieldName:%s,fieldValue:%s,extensionValue:%s,", fieldName, fieldValue, extensionValue);
        // 校验数据类型是否支持
        if(!supported(fieldClass)) {
            throw new IllegalArgumentException("The data type of `" + fieldName + "` isn't supported by the validator.");
        }
        // 数据类型支持，进行校验
        doValidate(fieldValue, extensionValue, errInfo);
    }

    protected abstract boolean supported(Class<?> fieldClass);
    
    protected abstract void doValidate(Object fieldValue, Object extensionValue, String errInfo) throws IllegalArgumentException;

}