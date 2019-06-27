 package cn.donespeak.protobufvalidation;

import java.util.HashSet;
import java.util.Set;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

import cn.donespeak.exchange.UserProto;

/**
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class GeneratedMessageV3Test {

    public static void main(String[] args) {
        UserProto.UserRequest messageV3 = UserProto.UserRequest.newBuilder()
            .setEmail("dfdf@e.com").setName(null).build();
       
        for (Descriptors.FieldDescriptor fieldDescriptor : messageV3.getDescriptorForType().getFields()) {
            
            System.out.println("field protoName: " + fieldDescriptor.getFile().getFullName());
            System.out.println("field name: " + fieldDescriptor.getFullName());
            // AS_UNRECOGNIZED
            System.out.println("field value: " + messageV3.getField(fieldDescriptor));
            // class com.google.protobuf.Descriptors$EnumValueDescriptor
            // class java.lang.Integer
            System.out.println("field class: " + messageV3.getField(fieldDescriptor).getClass());
            
            // cn.donespeak.exchange.UserRequest.active_statue
            System.out.println("descriptor fullname: " + fieldDescriptor.getFullName());
            // activeStatue
            System.out.println("descriptor jsonName: " + fieldDescriptor.getJsonName());
            // ENUM, class: class com.google.protobuf.Descriptors$FieldDescriptor$JavaType
            System.out.println("descriptor javaType: " + fieldDescriptor.getJavaType() + ", class: " + fieldDescriptor.getJavaType().getClass());
            // ENUM
            System.out.println("field type: " + fieldDescriptor.getType());
            
            System.out.println("=======================================");
        }
    }
}
