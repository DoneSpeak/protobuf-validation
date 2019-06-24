package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.LengthConstraint;
/**
 * Validate that the string is between min and max included.
 * 
 * {@see org.hibernate.validator.constraints.Length}
 */
public class LengthValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			throw new IllegalArgumentException("LengthValidator");			
		}
		
		if(!(fieldValue instanceof String)) {
			throw new IllegalArgumentException("LengthValidator");
		}
		
		LengthConstraint lengthConstraint = (LengthConstraint) extensionValue;
		int max = lengthConstraint.hasMax()? lengthConstraint.getMax(): Integer.MAX_VALUE;
		int min = lengthConstraint.hasMin()? lengthConstraint.getMin(): 0;
		
		int length = fieldValue.toString().length();
		
		if(length > max || length < min) {
			throw new IllegalArgumentException("LengthValidator");
		}
	}

}
