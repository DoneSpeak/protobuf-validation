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

import com.google.common.base.Preconditions;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 *
 * @author Serious
 * @date 2017/6/28
 */
public class MinValidator extends AbstractValidator {
	
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo) {
    	if(fieldValue == null || !(fieldValue instanceof Number)) {
    		return;
    	}
    	System.out.println("#### MinValidator.extensionValue: " + extensionValue);
        BigDecimal min = new BigDecimal(extensionValue.toString());
        BigDecimal value = new BigDecimal(fieldValue.toString());
        
        if(value.compareTo(min) < 0) {
        	throw new IllegalArgumentException("MinValidator");
        }
    }

    @Override
    public String toString() {
        return "MinValidator";
    }
}
