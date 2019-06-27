 package cn.donespeak.protobufvalidation;

import com.google.protobuf.Descriptors;

/**
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class ProtoConstraintViolation {
    private String propertyPath;
    private String propertyName;
    private Object invalidValue;
    private String message;
    
    private Descriptors.FieldDescriptor fieldDescriptor;

    public String getPropertyPath() {
        return propertyPath;
    }
    public void setPropertyPath(String protertyPath) {
        this.propertyPath = protertyPath;
    }
    public void setPropertyPath(String propertyPath, String propertyName) {
        if(propertyPath == null || propertyPath.isEmpty()) {
            this.propertyPath = propertyName;
        } else {
            this.propertyPath = propertyName + "." + propertyName;
        }
    }
    public String getPropertyName() {
        return propertyName;
    }
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public Object getInvalidValue() {
        return invalidValue;
    }
    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Descriptors.FieldDescriptor getFieldDescriptor() {
        return fieldDescriptor;
    }
    public void setFieldDescriptor(Descriptors.FieldDescriptor fieldDescriptor) {
        this.fieldDescriptor = fieldDescriptor;
    }
    
    @Override
    public String toString() {
        return "ProtoConstraintViolation [propertyPath=" + propertyPath + ", propertyName=" + propertyName
            + ", invalidValue=" + invalidValue + ", message=" + message + ", fieldDescriptor=" + fieldDescriptor + "]";
    }
}
