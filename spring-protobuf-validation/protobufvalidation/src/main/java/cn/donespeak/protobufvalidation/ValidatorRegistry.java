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
    private static final Map<Descriptors.FieldDescriptor, ContraintValidator> REGISTRY = Maps.newHashMap();

    static {
        REGISTRY.put(ProtoValidation.notNull.getDescriptor(), new NotNullValidator());
        REGISTRY.put(ProtoValidation.assertBool.getDescriptor(), new AssertBoolValidator());
        REGISTRY.put(ProtoValidation.min.getDescriptor(), new MinValidator());
        REGISTRY.put(ProtoValidation.max.getDescriptor(), new MaxValidator());
        REGISTRY.put(ProtoValidation.size.getDescriptor(), new SizeValidator());
        REGISTRY.put(ProtoValidation.digits.getDescriptor(), new DigitsValidator());
        REGISTRY.put(ProtoValidation.past.getDescriptor(), new PastValidator());
        REGISTRY.put(ProtoValidation.future.getDescriptor(), new FutureValidator());
        REGISTRY.put(ProtoValidation.pattern.getDescriptor(), new PatternValidator());
        REGISTRY.put(ProtoValidation.notBlank.getDescriptor(), new NotBlankValidator());
        REGISTRY.put(ProtoValidation.notEmpty.getDescriptor(), new NotEmptyValidator());
        REGISTRY.put(ProtoValidation.positive.getDescriptor(), new PositiveValidator());
        REGISTRY.put(ProtoValidation.negative.getDescriptor(), new NegativeValidator());
        REGISTRY.put(ProtoValidation.range.getDescriptor(), new RangeValidator());
        REGISTRY.put(ProtoValidation.length.getDescriptor(), new LengthValidator());
        REGISTRY.put(ProtoValidation.email.getDescriptor(), new EmailValidator());
    }

    public static ContraintValidator getValidator(Descriptors.FieldDescriptor descriptor) {
        return REGISTRY.get(descriptor);
    }
}