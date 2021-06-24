package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated element must not be {@code null}.
 * Accepts any type.
 * 
 * {@see javax.validation.constraints.NotNull}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class NotNullValidator extends AbstractValidator {

    @Override
    protected boolean supported(Class<?> fieldClass) {
        return true;
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		boolean notNull = (boolean) extensionValue;
		
		if(notNull == (fieldValue == null)) {
			throw new IllegalArgumentException("校验不通过");
		}
	}
}