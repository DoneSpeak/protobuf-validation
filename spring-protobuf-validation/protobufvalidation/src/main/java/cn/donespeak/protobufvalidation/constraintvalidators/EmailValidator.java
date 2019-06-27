package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.EmailUtil;

/**
 * The string has to be a well-formed email address. Exact semantics of what makes up a valid
 * email address are left to Bean Validation providers. Accepts {@code CharSequence}.
 *
 * {@code null} elements are considered valid.
 * 
 * {@see javax.validation.constraints.Max}
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class EmailValidator extends AbstractValidator {

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
		if(!EmailUtil.isValidEmail(fieldValue.toString())) {
			throw new IllegalArgumentException("EmailValidator");
		}
	}
}