/*
 * Copyright 2017-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.donespeak.protobufvalidation;

import com.google.common.collect.Maps;
import com.google.protobuf.Descriptors;

import cn.donespeak.protobufvalidation.constraintvalidators.*;
import valid.ProtoValidation;

import java.util.Map;

/**
 * @author Serious
 */
public class ValidatorRegistry {
    private static final Map<Descriptors.FieldDescriptor, Validator> REGISTRY = Maps.newHashMap();

    static {
        REGISTRY.put(ProtoValidation.notNull.getDescriptor(), new MaxValidator());
        REGISTRY.put(ProtoValidation.assertBool.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.min.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.max.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.size.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.digits.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.past.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.future.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.pattern.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.notBlank.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.notEmpty.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.positive.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.negative.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.range.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.length.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.email.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.in.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.notIn.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.among.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.notAmong.getDescriptor(), new MinValidator());
    }

    public static Validator getValidator(Descriptors.FieldDescriptor descriptor) {
        return REGISTRY.get(descriptor);
    }
}