 package cn.donespeak.protobufvalidation.util;

import java.util.Map;

import com.google.protobuf.Descriptors;

import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;

/**
 * @author Guanrong Yang
 * @date 2019/06/29
 */
public class FieldConstraintToPair {

    public static FieldConstraintPair toPair(FieldConstraint fieldConstraint) {
        ConstraintCase constraintCase = fieldConstraint.getConstraintCase();
        Object value = null;
        for(Map.Entry<Descriptors.FieldDescriptor, Object> entry: fieldConstraint.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor descriptor = entry.getKey();
            // 第一个oneof属性则为目标值
            if(descriptor.toProto().hasOneofIndex()) {
                value = entry.getValue();
            }
        }
        return new FieldConstraintPair(constraintCase, value);
    }
    
    public static class FieldConstraintPair {
        private final ConstraintCase constraintCase;
        private final Object constraintValue;
        
        FieldConstraintPair(ConstraintCase constraintCase, Object constraintValue) {
            this.constraintCase = constraintCase;
            this.constraintValue = constraintValue;
        }
        public ConstraintCase getConstraintCase() {
            return constraintCase;
        }
        public Object getConstraintValue() {
            return constraintValue;
        }
    }
}
