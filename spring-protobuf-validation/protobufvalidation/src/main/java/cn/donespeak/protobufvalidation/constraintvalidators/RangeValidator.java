package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.RangeConstraint;
/**
 * The annotated element has to be in the appropriate range. Apply on numeric fieldValues or string
 * representation of the numeric fieldValue.
 * 
 * {@see org.hibernate.validator.constraints.Range}
 * 
 * @author Guanrong Yang
 */
public class RangeValidator extends AbstractValidator {

    private MinValidator minValidator = new MinValidator();
    private MaxValidator maxValidator = new MaxValidator();
    
    @Override
    protected boolean supported(Class<?> fieldClass) {
        return minValidator.supported(fieldClass) && maxValidator.supported(fieldClass);
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		
		if(fieldValue == null) {
			return;
		}
		
		RangeConstraint rangeConstraint = (RangeConstraint) extensionValue;
		long max = rangeConstraint.hasMax()? rangeConstraint.getMax(): Long.MAX_VALUE;
		long min = rangeConstraint.hasMin()? rangeConstraint.getMin(): 0;
		
		try {
		    minValidator.doValidate(fieldValue, min, errInfo);
            maxValidator.doValidate(fieldValue, max, errInfo);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("RangeValidator");
		}
	}
}
