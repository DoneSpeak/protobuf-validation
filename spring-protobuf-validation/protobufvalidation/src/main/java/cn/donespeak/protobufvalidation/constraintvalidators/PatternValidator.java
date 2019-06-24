package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.regex.Pattern;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated {@code CharSequence} must match the specified regular expression.
 * The regular expression follows the Java regular expression conventions
 * see {@link java.util.regex.Pattern}.
 * <p>
 * Accepts {@code CharSequence}. {@code null} elements are considered valid.
 * 
 * {@see javax.validation.constraints.Pattern}
 * {@see org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator}
 * 
 * @author Guanrong Yang
 *
 */
public class PatternValidator  extends AbstractValidator {
	
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
    		throws IllegalArgumentException {
    	if(fieldValue == null) {
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
