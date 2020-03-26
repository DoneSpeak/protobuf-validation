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

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.protobuf.Descriptors;

import cn.donespeak.protobufvalidation.constraintvalidators.AssertBoolValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.DigitsValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.EmailValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.FutureValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.LengthValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.MaxValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.MinValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.NegativeValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.NotBlankValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.NotEmptyValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.NotNullValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.PastValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.PatternValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.PositiveValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.RangeValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.SizeValidator;
import valid.FieldConstraint;
import valid.FieldConstraint.ConstraintCase;

/**
 * @author Serious
 */
public class ValidatorRegistry {
    private static final Map<ConstraintCase, ContraintValidator> REGISTRY = Maps.newHashMap();

    static {
//        REGISTRY.put(ConstraintCase.NOT_NULL, new NotNullValidator());
        REGISTRY.put(ConstraintCase.ASSERT_BOOL, new AssertBoolValidator());
        REGISTRY.put(ConstraintCase.MIN, new MinValidator());
        REGISTRY.put(ConstraintCase.MAX, new MaxValidator());
        REGISTRY.put(ConstraintCase.SIZE, new SizeValidator());
        REGISTRY.put(ConstraintCase.DIGITS, new DigitsValidator());
        REGISTRY.put(ConstraintCase.PAST, new PastValidator());
        REGISTRY.put(ConstraintCase.FUTURE, new FutureValidator());
        REGISTRY.put(ConstraintCase.PATTERN, new PatternValidator());
        REGISTRY.put(ConstraintCase.NOT_BLANK, new NotBlankValidator());
        REGISTRY.put(ConstraintCase.NOT_EMPTY, new NotEmptyValidator());
        REGISTRY.put(ConstraintCase.POSITIVE, new PositiveValidator());
        REGISTRY.put(ConstraintCase.NEGATIVE, new NegativeValidator());
        REGISTRY.put(ConstraintCase.RANGE, new RangeValidator());
        REGISTRY.put(ConstraintCase.LENGTH, new LengthValidator());
        REGISTRY.put(ConstraintCase.EMAIL, new EmailValidator());
//        REGISTRY.put(ConstraintCase.REQUIRED, new RequiredValidator());
//        REGISTRY.put(ConstraintCase.COLLECTION, new CollectionValidator());
//        REGISTRY.put(ConstraintCase.SEQUENCES_IN, new SequencesInValidator());
//        REGISTRY.put(ConstraintCase.NUMBERS_IN, new NumbersINValidator());
    }

    public static ContraintValidator getValidator(ConstraintCase constraintCase) {
        return REGISTRY.get(constraintCase);
    }
    
    public static boolean isRegistered(ConstraintCase constraintCase) {
        return REGISTRY.containsKey(constraintCase);
    }
}