package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated element must not be {@code null} and must contain at least one
 * non-whitespace character. Accepts {@code CharSequence}.
 * 
 * {@see javax.validation.constraints.NotBlank}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class NotBlankValidator extends AbstractValidator {
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
		boolean isNotBlanckRequired = (Boolean) extensionValue;
		
		boolean isBlank = fieldValue == null || fieldValue.toString().trim().isEmpty();
		if(isBlank == isNotBlanckRequired) {
			throw new IllegalArgumentException("");
		}
	}
}
