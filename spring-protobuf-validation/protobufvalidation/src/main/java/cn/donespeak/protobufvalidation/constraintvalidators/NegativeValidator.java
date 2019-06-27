package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.NumberUtil;

/**
 * The annotated element must be a strictly negative number (i.e. 0 is considered as an
 * invalid value).
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code byte}, {@code short}, {@code int}, {@code long}, {@code float},
 *     {@code double} and their respective wrappers</li>
 * </ul>
 * <p>
 * {@code null} elements are considered valid.
 * {@see javax.validation.constraints.Negative}
 * 
 * @author Guanrong Yang
 */
public class NegativeValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			return;
		}
		
		Boolean isPositive = NumberUtil.isPossitive(fieldValue);
		
		if(isPositive == true) {
			throw new IllegalArgumentException("");
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
