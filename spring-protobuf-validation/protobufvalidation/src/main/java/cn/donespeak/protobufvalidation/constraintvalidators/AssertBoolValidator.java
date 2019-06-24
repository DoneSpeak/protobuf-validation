package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

public class AssertBoolValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		// 数据不是目标类型，直接校验失败
		if(fieldValue == null || !(fieldValue instanceof Boolean)) {
			throw new IllegalArgumentException("");
		}
		Boolean fieldBoolValue = Boolean.valueOf(fieldValue.toString());
		Boolean extensionBoolValue = Boolean.valueOf(extensionValue.toString());
		
		if(fieldBoolValue != extensionBoolValue) {
			throw new IllegalArgumentException("");
		}
	}
}