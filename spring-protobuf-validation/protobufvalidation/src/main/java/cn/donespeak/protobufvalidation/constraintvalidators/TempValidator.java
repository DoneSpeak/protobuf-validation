 package cn.donespeak.protobufvalidation.constraintvalidators;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MapEntry;

import cn.donespeak.protobufvalidation.ContraintValidator;
import cn.donespeak.protobufvalidation.ValidatorRegistry;
import cn.donespeak.protobufvalidation.util.FieldConstraintToPair;
import cn.donespeak.protobufvalidation.util.FieldConstraintToPair.FieldConstraintPair;
import valid.CollectionConstraint;
import valid.FieldConstraint;

/**
 * @author Guanrong Yang
 * @date 2019/06/29
 */
public class TempValidator {
    
    public boolean supported(Descriptors.FieldDescriptor fieldDescriptor, Class<?> fieldClass) {
        return fieldDescriptor.isMapField();
    }
    
    public boolean isValid(GeneratedMessageV3 messageV3, Descriptors.FieldDescriptor fieldDescriptor, CollectionConstraint constraintValue ) {
        if(!constraintValue.hasKeys() && !constraintValue.hasValues()) {
            return true;
        }
        int repeatedCount = messageV3.getRepeatedFieldCount(fieldDescriptor);
        
        for(int i = 0; i < repeatedCount; i ++) {
            MapEntry<?, ?> mapValue = (MapEntry<?, ?>)messageV3.getRepeatedField(fieldDescriptor, i);
            String fieldName = fieldDescriptor.getFullName() + "[" + mapValue.getKey().toString() + "]";
            if(constraintValue.hasKeys()) {
                validate(constraintValue.getKeys(), fieldName + ".key", mapValue.getKey());
            }
            if(constraintValue.hasValues()) {
                validate(constraintValue.getValues(), fieldName + ".val", mapValue.getValue());
            }
        }
        return false;
    }
    
    private boolean validate(valid.FieldConstraint fieldConstraint, String fieldName, Object fieldValue) {
        FieldConstraintPair pair = FieldConstraintToPair.toPair(fieldConstraint);
        ContraintValidator validator = ValidatorRegistry.getValidator(pair.getConstraintCase());
        if(validator == null) {
            // 对于不存在的validator的option，直接跳过
            return true;
        }
        validator.validate(fieldClass, fieldName, fieldValue, pair.getConstraintValue());
    }

}
