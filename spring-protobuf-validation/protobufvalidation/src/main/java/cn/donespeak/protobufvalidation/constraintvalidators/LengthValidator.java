package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.LengthConstraint;

/**
 * Validate that the string is between min and max included.
 * 
 * {@code null} elements are considered valid.
 * 
 * {@see org.hibernate.validator.constraints.Length}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class LengthValidator extends AbstractValidator {

    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();
    
    static {
        SUPPORTED_CLASSES.add(CharSequence.class);
    }
    
    @Override
    protected boolean supported(Class<?> fieldClass) {
        return SUPPORTED_CLASSES.contains(fieldClass);
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			// null值通过校验
		    return;
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
