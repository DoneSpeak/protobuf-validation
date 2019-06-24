package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.EmailUtil;

public class EmailValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(!(fieldValue instanceof String)) {
			return;
		}
		if(!EmailUtil.isValidEmail(fieldValue.toString())) {
			throw new IllegalArgumentException("EmailValidator");
		}
	}
}