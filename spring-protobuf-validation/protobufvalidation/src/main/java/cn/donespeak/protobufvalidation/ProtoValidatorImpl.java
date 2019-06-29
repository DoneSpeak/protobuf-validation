 package cn.donespeak.protobufvalidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;

import cn.donespeak.protobufvalidation.util.FieldConstraintToPair;
import cn.donespeak.protobufvalidation.util.FieldConstraintToPair.FieldConstraintPair;

import com.google.protobuf.GeneratedMessageV3;

import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;
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
            // 对所有属性进行逐一校验
            if (fieldDescriptor.getOptions().getAllFields().size() > 0) {
                Object fieldValue = messageV3.getField(fieldDescriptor);

                if (fieldValue instanceof GeneratedMessageV3) {
                    // 如果 field 被 validate 修饰，且类型为 message 时，则需要进一步校验
                    GeneratedMessageV3 subMessageV3 = (GeneratedMessageV3) messageV3.getField(fieldDescriptor);
                    String newPropertyPath = appendPropertyPath(propertyPath, fieldDescriptor.getJsonName());
                    messageFields.put(newPropertyPath, subMessageV3);
                } else if(fieldDescriptor.isMapField()) {
                    
                } else if(fieldDescriptor.getJavaType() == JavaType.ENUM) {
                    System.out.println(fieldDescriptor.getName() + "; enumType: " + fieldDescriptor.getEnumType());
                }
                
                // 对一个有 option 的字段进行校验
                resultValidations.addAll(doValidate(propertyPath, messageV3.getField(fieldDescriptor).getClass(), 
                    fieldDescriptor.getJsonName(), fieldValue, 
                    fieldDescriptor.getOptions()));
            }
        }

        // 对所有validate的message field进行校验
        for(Map.Entry<String, GeneratedMessageV3> messageField: messageFields.entrySet()) {
            resultValidations.addAll(validate(messageField.getKey(), messageField.getValue()));
        }
        
        return resultValidations;
    }
    
    private Set<ProtoConstraintViolation> doValidate(String propertyPath, GeneratedMessageV3 messageV3, Descriptors.FieldDescriptor fieldDescriptor) {

        Set<ProtoConstraintViolation> resultValidations = new HashSet<ProtoConstraintViolation>();
    
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fieldDescriptor.getOptions().getAllFields().entrySet()) {
            Descriptors.FieldDescriptor optionDescriptor = entry.getKey();
            
            Object optionValue = entry.getValue();
            if(!(optionValue instanceof FieldConstraint)) {
                // 仅 FieldConstraint 支持做校验
                continue;
            }

            FieldConstraintPair pair = FieldConstraintToPair.toPair((FieldConstraint) optionValue);
            ContraintValidator validator = ValidatorRegistry.getValidator(pair.getConstraintCase());
            if(validator == null) {
                // 对于不存在的validator的option，直接跳过
                continue;
            }
            Object fieldValue = messageV3.getField(fieldDescriptor);
            
            try {
                validator.validate(messageV3, fieldValue, pair.getConstraintValue());
            } catch (IllegalArgumentException e) {
                ProtoConstraintViolation constraintViolation = new ProtoConstraintViolation();
                constraintViolation.setInvalidValue(fieldValue);
                constraintViolation.setPropertyName(fieldName);
                constraintViolation.setPropertyPath(appendPropertyPath(propertyPath, fieldName));
                constraintViolation.setMessage(optionDescriptor.toString());
                
                resultValidations.add(constraintViolation);                
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
            ContraintValidator validator = null;
            Object constraintValue = null;
            if(optionValue instanceof FieldConstraint) {
                FieldConstraint fieldConstraint = (FieldConstraint) optionValue;
                FieldConstraintPair pair = FieldConstraintToPair.toPair(fieldConstraint);
                // 仅 FieldConstraint 支持做校验
                validator = ValidatorRegistry.getValidator(pair.getConstraintCase());
                constraintValue = pair.getConstraintValue();
            }
            if(validator == null) {
                // 对于不存在的validator的option，直接跳过
                continue;
            }
            try {
                validator.validate(fieldClass, fieldName, fieldValue, constraintValue);
            } catch (IllegalArgumentException e) {
                ProtoConstraintViolation constraintViolation = new ProtoConstraintViolation();
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
