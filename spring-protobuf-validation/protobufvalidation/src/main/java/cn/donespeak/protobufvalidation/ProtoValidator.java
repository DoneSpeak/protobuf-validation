package cn.donespeak.protobufvalidation;

import java.util.Map;
import java.util.Set;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;

public interface ProtoValidator {
    <T extends GeneratedMessageV3> Set<ProtoConstraintViolation> validate(T messageV3);
}