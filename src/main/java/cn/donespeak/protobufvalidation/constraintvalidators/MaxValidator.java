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

package cn.donespeak.protobufvalidation.constraintvalidators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated element must be a number whose value must be lower or
 * equal to the specified maximum.
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code byte}, {@code short}, {@code int}, {@code long}, and their respective
 *     wrappers</li>
 * </ul>
 * Note that {@code double} and {@code float} are not supported due to rounding errors
 * (some providers might provide some approximative support).
 * <p>
 * {@code null} elements are considered valid.
 * 
 * {@see javax.validation.constraints.Max}
 */

public class MaxValidator extends AbstractValidator {

    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        SUPPORTED_CLASSES.add(BigDecimal.class);
        SUPPORTED_CLASSES.add(BigInteger.class);
        // number types
        SUPPORTED_CLASSES.add(Byte.class);
        SUPPORTED_CLASSES.add(Short.class);
        SUPPORTED_CLASSES.add(Integer.class);
        SUPPORTED_CLASSES.add(Long.class);
    }
    
    @Override
    protected boolean supported(Class<?> fieldClass) {
        return SUPPORTED_CLASSES.contains(fieldClass);
    }
    
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
    		throws IllegalArgumentException {
    	if(fieldValue == null) {
    		return;
    	}
        BigDecimal max = new BigDecimal(extensionValue.toString());
        BigDecimal value = new BigDecimal(fieldValue.toString());
        
        if(value.compareTo(max) > 0) {
        	throw new IllegalArgumentException("MaxValidator");
        }
    }

    @Override
    public String toString() {
        return "MaxValidator";
    }

}
