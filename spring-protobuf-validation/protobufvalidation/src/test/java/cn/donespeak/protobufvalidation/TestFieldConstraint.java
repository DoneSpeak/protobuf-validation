 package cn.donespeak.protobufvalidation;

import java.util.Map;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

import cn.donespeak.exchange.UserProto;
import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;

/**
 * @author Guanrong Yang
 * @date 2019/06/28
 */
public class TestFieldConstraint {

    public static void main(String[] args) {
        FieldConstraint fieldConstraint = FieldConstraint.newBuilder().setAssertBool(true).build();
        ConstraintCase contraintCase = fieldConstraint.getConstraintCase();
       
//        fieldConstraint.
        System.out.println(contraintCase);
        
        test(fieldConstraint);
        System.out.println("@#################@");
        UserProto.UserRequest userRequest = UserProto.UserRequest.newBuilder()
            .setEmail("dfdf@e.com").setName("").build();
        
        test(userRequest);
    }
    
    private static void test(GeneratedMessageV3 messageV3) {
        for(Map.Entry<Descriptors.FieldDescriptor, Object> entry: messageV3.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor descriptor = entry.getKey();
            Object value = entry.getValue();
            System.out.println("# fieldValue: " + messageV3.getField(descriptor));
            Descriptors.OneofDescriptor oneof = descriptor.getContainingOneof();
            System.out.println("# isOneof: " + (oneof != null));
            System.out.println("# hasOneofIndex: " + descriptor.toProto().hasOneofIndex());
            System.out.println("# fileValue: " + value);
        }
    }
}