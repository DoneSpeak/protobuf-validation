package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * 校验CharSequence类型
 * @author yangguanrong
 *
 */
public class NotBlankValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(!(fieldValue instanceof CharSequence)) {
			return;
		}
		boolean isNotBlanckRequired = (Boolean) extensionValue;
		
		boolean isBlank = fieldValue == null || fieldValue.toString().trim().isEmpty();
		if(isBlank == isNotBlanckRequired) {
			throw new IllegalArgumentException("");
		}
	}
}
