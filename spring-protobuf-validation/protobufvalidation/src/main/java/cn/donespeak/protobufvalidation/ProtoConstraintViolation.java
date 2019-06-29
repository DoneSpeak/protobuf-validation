 package cn.donespeak.protobufvalidation;

/**
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class ProtoConstraintViolation {
    private String propertyPath;
    private String propertyName;
    private Object invalidValue;
    private String message;
    
    public String getPropertyPath() {
        return propertyPath;
    }
    public void setPropertyPath(String protertyPath) {
        this.propertyPath = protertyPath;
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

    @Override
    public String toString() {
        return "ProtoConstraintViolation [propertyPath=" + propertyPath + ", propertyName=" + propertyName
            + ", invalidValue=" + invalidValue + ", message=" + message + "]";
    }
}
