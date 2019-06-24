package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * {@see javax.validation.constraints.Future}
 * 
 * @author Guanrong Yang
 *
 */
public class FutureValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			return;
		}
		long now = System.currentTimeMillis();
		long timeMillis = 0;
		if(fieldValue instanceof Long) {
			timeMillis = (Long) fieldValue;
		} else if (fieldValue instanceof Integer) {
			timeMillis = 1000L * (Integer) fieldValue;
		}
		if(timeMillis <= now) {
			throw new IllegalArgumentException("");
		}
	}
	
}
