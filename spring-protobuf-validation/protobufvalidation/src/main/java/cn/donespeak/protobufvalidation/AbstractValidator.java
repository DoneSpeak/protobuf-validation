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
        
        doValidate(fieldValue, extensionValue, errInfo);
    }

    protected abstract void supported(Object fieldValue) throws IllegalArgumentException;
    
    protected abstract void doValidate(Object fieldValue, Object extensionValue, String errInfo) throws IllegalArgumentException;

}