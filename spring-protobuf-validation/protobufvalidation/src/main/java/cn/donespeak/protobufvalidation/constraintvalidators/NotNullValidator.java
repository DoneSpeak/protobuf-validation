package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

public class NotNullValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		boolean notNull = (boolean) extensionValue;
		
		if(notNull == (fieldValue == null)) {
			throw new IllegalArgumentException("校验不通过");
		}
	}

    /* (non-Javadoc)
     * @see cn.donespeak.protobufvalidation.AbstractValidator#supported(java.lang.Object)
     */
    @Override
    protected void supported(Object fieldValue) throws IllegalArgumentException {
        // TODO Auto-generated method stub
         
    }
}