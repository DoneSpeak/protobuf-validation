 package cn.donespeak.protobufvalidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

import valid.ProtoValidation;

/**
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class ProtoValidatorImpl implements ProtoValidator {

    @Override
    public <T extends GeneratedMessageV3> Set<ProtoConstraintViolation> validate(T messageV3) {
        return validate("", messageV3);
    }
    
    public <T extends GeneratedMessageV3> Set<ProtoConstraintViolation> validate(String propertyPath, T messageV3) {
        Set<ProtoConstraintViolation> resultValidations = new HashSet<ProtoConstraintViolation>();
        Map<String, GeneratedMessageV3> messageFields = new HashMap<String, GeneratedMessageV3>();
        
        for (Descriptors.FieldDescriptor fieldDescriptor : messageV3.getDescriptorForType().getFields()) {
            if (fieldDescriptor.getOptions().getAllFields().size() > 0) {
                Object fieldValue = messageV3.getField(fieldDescriptor);
                // 对一个有 option 的字段进行校验
                resultValidations.addAll(doValidate(propertyPath, messageV3.getField(fieldDescriptor).getClass(), 
                    fieldDescriptor.getJsonName(), fieldValue, 
                    fieldDescriptor.getOptions()));
                
                if (fieldValue != null && fieldValue instanceof GeneratedMessageV3) {
                    // 如果 field 被 validate 修饰，且类型为 message 时，则需要进一步校验
                    // 如果该 field 为 null，则不做校验
                    GeneratedMessageV3 subMessageV3 = (GeneratedMessageV3) messageV3.getField(fieldDescriptor);
                    // 对 message 的 options 进行检查
                    for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fieldDescriptor.getOptions().getAllFields().entrySet()) {
                        Descriptors.FieldDescriptor optionDescriptor = entry.getKey();

                        if(ProtoValidation.validate.getDescriptor().equals(optionDescriptor)) {
                            boolean validated = (boolean)entry.getValue();
                            if(validated) {
                                String newPropertyPath = appendPropertyPath(propertyPath, fieldDescriptor.getJsonName());
                                messageFields.put(newPropertyPath, subMessageV3);
                                break;
                            }
                        }
                    }
                }
            }
            // 对所有validate的message field进行校验
            for(Map.Entry<String, GeneratedMessageV3> messageField: messageFields.entrySet()) {
                resultValidations.addAll(validate(messageField.getKey(), messageField.getValue()));
            }
        }
        return resultValidations;
    }
    
    private Set<ProtoConstraintViolation> doValidate(String propertyPath, Class<?> fieldClass, String fieldName, Object fieldValue,  
            DescriptorProtos.FieldOptions options) {

        Set<ProtoConstraintViolation> resultValidations = new HashSet<ProtoConstraintViolation>();
        
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : options.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor optionDescriptor = entry.getKey();
            Object optionValue = entry.getValue();
            
            ContraintValidator validator = ValidatorRegistry.getValidator(optionDescriptor);
            if(validator == null) {
                // 对于不存在的validator的option，直接跳过
                continue;
            }
            try {
                validator.validate(fieldClass, fieldName, fieldValue, optionValue);
            } catch (IllegalArgumentException e) {
                ProtoConstraintViolation constraintViolation = new ProtoConstraintViolation();
                constraintViolation.setFieldDescriptor(optionDescriptor);
                constraintViolation.setInvalidValue(fieldValue);
                constraintViolation.setPropertyName(fieldName);
                constraintViolation.setPropertyPath(appendPropertyPath(propertyPath, fieldName));
                constraintViolation.setMessage(optionDescriptor.toString());
                
                resultValidations.add(constraintViolation);                
            }
        }
        return resultValidations;
    }
    
    private String appendPropertyPath(String propertyPath, String propertyName) {
        if(propertyPath == null || propertyPath.isEmpty()) {
            return propertyName;
        } else {
            return propertyPath + "." + propertyName;
        }
    }
}
