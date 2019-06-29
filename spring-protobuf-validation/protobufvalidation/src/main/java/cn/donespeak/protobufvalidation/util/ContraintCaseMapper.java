 package cn.donespeak.protobufvalidation.util;

import java.util.List;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;

import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;

/**
 * @author Guanrong Yang
 * @date 2019/06/28
 */
public class ContraintCaseMapper {

    public static ConstraintCase getContraintCase(FieldConstraint fieldConstraint) {
        List<OneofDescriptor> oneofs = FieldConstraint.getDescriptor().getOneofs();
        return null;
    }
    
    public static void main(String[] args) {
        for(ConstraintCase c: ConstraintCase.values()) {
            System.out.println(c);
        }
        FieldConstraint fieldConstraint = FieldConstraint.newBuilder().setAssertBool(true).build();
        System.out.println(fieldConstraint.toBuilder().getConstraintCase());
        
        System.out.println(fieldConstraint.toBuilder().clear().getConstraintCase());
    }
}
