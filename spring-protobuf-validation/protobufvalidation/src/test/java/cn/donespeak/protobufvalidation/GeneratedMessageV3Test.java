 package cn.donespeak.protobufvalidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.protobuf.DescriptorProtos.FieldOptions;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MapEntry;
import com.google.protobuf.Message;

import cn.donespeak.exchange.NewValidation;
import cn.donespeak.exchange.NewValidation.Item;
import cn.donespeak.exchange.NewValidation.State;
import cn.donespeak.exchange.UserProto;
import valid.ProtoValidation;
import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;

/**
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class GeneratedMessageV3Test {

    public static void main(String[] args) {
        test();
        showMessageV3();
    }
    
    public static void showMessageV3() {
        UserProto.UserRequest userRequest = UserProto.UserRequest.newBuilder()
            .setEmail("dfdf@e.com").setName("").build();
       
        Map<String, Item> map = new HashMap<String, Item>();
        map.put("dfd", NewValidation.Item.newBuilder().setVal("df").build());
        map.put("33", NewValidation.Item.newBuilder().setVal("df2222").build());
        
        NewValidation.NewData newData = NewValidation.NewData.newBuilder().addAllNumbers(Arrays.asList(1, 4, 5))
                .putAllScores(map).addAllItems(map.values()).setState(State.S_PENDING).build();

        GeneratedMessageV3 messageV3 = newData;
        
        for (Descriptors.FieldDescriptor fieldDescriptor : messageV3.getDescriptorForType().getFields()) {
            
            System.out.println("field protoName: " + fieldDescriptor.getFile().getFullName());
            System.out.println("field name: " + fieldDescriptor.getFullName());
            // AS_UNRECOGNIZED
            System.out.println("field value: " + messageV3.getField(fieldDescriptor));
            System.out.println("field is list: " + (messageV3.getField(fieldDescriptor) instanceof List));
            if(fieldDescriptor.isMapField()) {
                int repeatedCount = messageV3.getRepeatedFieldCount(fieldDescriptor);
                
                for(int i = 0; i < repeatedCount; i ++) {
                    MapEntry<?, ?> mapValue = (MapEntry<?, ?>)messageV3.getRepeatedField(fieldDescriptor, i);
                    System.out.println("mapValue.class: " + mapValue.getClass() + ", mapValue.key: " + mapValue.getKey() + ", mapValue.value: " + mapValue.getValue());
                }
            } else if(fieldDescriptor.isRepeated()) {
                int repeatedCount = messageV3.getRepeatedFieldCount(fieldDescriptor);
                
                for(int i = 0; i < repeatedCount; i ++) {
                    Object repeatedValue = messageV3.getRepeatedField(fieldDescriptor, i);
                    System.out.println("repeatedValue.class: " + repeatedValue.getClass() + ", repeatedValue.value: " + repeatedValue);
                }
            }
            // class com.google.protobuf.Descriptors$EnumValueDescriptor
            // class java.lang.Integer
            System.out.println("field class: " + messageV3.getField(fieldDescriptor).getClass());
//            Class k = EnumValueDescriptor.class;
            System.out.println("field enumType: " + (fieldDescriptor.getJavaType() == JavaType.ENUM));
            if(fieldDescriptor.getJavaType() == JavaType.ENUM) {
                EnumValueDescriptor enumValue = (EnumValueDescriptor) messageV3.getField(fieldDescriptor);
                System.out.println("field enum value: " + enumValue.getNumber());
            }
            // cn.donespeak.exchange.UserRequest.active_statue
            System.out.println("descriptor fullname: " + fieldDescriptor.getFullName());
            
            // activeStatue
            System.out.println("descriptor jsonName: " + fieldDescriptor.getJsonName());
            // ENUM, class: class com.google.protobuf.Descriptors$FieldDescriptor$JavaType
            System.out.println("descriptor javaType: " + fieldDescriptor.getJavaType() + ", class: " + fieldDescriptor.getJavaType().getClass());
            // ENUM
            System.out.println("field type: " + fieldDescriptor.getType());
            
            System.out.println("field options: " + fieldDescriptor.getOptions());
            
            processOption(fieldDescriptor.getOptions());
            
            System.out.println("=======================================");
            
        }
    }
    
    private static void processOption(FieldOptions fieldOptions) {
        System.out.println("########### processOption ############");
        FieldConstraint rules = null;
        for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : fieldOptions.getAllFields().entrySet()) {
            Descriptors.FieldDescriptor optionDescriptor = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key: " + optionDescriptor);
            System.out.println("value: " + value.getClass() + ", " + value);
            if(value instanceof FieldConstraint) {
                rules = (FieldConstraint) value;
            }
        }
        if(rules != null) {
            for(Map.Entry<FieldDescriptor, Object> entry: rules.getAllFields().entrySet()) {
                FieldDescriptor field = entry.getKey();
                
                OneofDescriptor oneof = field.getContainingOneof();
                System.out.println("getContainingOneof: " + oneof.getFullName());
                System.out.println("getContainingType: " + field.getContainingType().getFullName());
                System.out.println("oneof index: " + field.toProto().getOneofIndex());
                System.out.println("fieldDes: " + field);
                System.out.println("value: " + entry.getValue());
                System.out.println("number: " + field.getNumber());
            }
        }
        System.out.println("##################################");
    }
    
    public static void test() {
        ConstraintCase caseEmail = ConstraintCase.EMAIL;
        System.out.println(caseEmail);
    }
    
    public static void testOneof() {
        FieldConstraint field = FieldConstraint.newBuilder().setEmail(true).build();
//        System.out.println(field.getField(field));
    }
}
