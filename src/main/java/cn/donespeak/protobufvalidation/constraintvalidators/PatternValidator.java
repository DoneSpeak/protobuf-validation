package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated {@code CharSequence} must match the specified regular expression.
 * The regular expression follows the Java regular expression conventions
 * see {@link java.util.regex.Pattern}.
 * <p>
 * Accepts {@code CharSequence}. 
 * 
 * {@code null} elements are considered valid.
 * 
 * {@see javax.validation.constraints.Pattern}
 * {@see org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class PatternValidator  extends AbstractValidator {
    
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
        String extensionValueStr = extensionValue.toString();
        String fieldValueStr = fieldValue.toString();

        Pattern pattern = Pattern.compile(extensionValueStr);
        if(!pattern.matcher(fieldValueStr).matches()) {
        	throw new IllegalArgumentException("");
        }
    }
}
