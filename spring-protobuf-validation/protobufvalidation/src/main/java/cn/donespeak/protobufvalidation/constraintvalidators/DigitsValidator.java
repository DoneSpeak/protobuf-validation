package cn.donespeak.protobufvalidation.constraintvalidators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.DigitsConstraint;
/** 
 * The annotated element must be a number within accepted range
 * Supported types are:
 * <ul>
 *     <li>{@code BigDecimal}</li>
 *     <li>{@code BigInteger}</li>
 *     <li>{@code CharSequence}</li>
 *     <li>{@code byte}, {@code short}, {@code int}, {@code long}, and their respective
 *     wrapper types</li>
 * </ul>
 * <p/>
 * {@code null} elements are considered valid.
 * {@see javax.validation.constraints.Digits}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class DigitsValidator extends AbstractValidator {

    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        SUPPORTED_CLASSES.add(BigDecimal.class);
        SUPPORTED_CLASSES.add(BigInteger.class);
        SUPPORTED_CLASSES.add(CharSequence.class);
        // number types
        SUPPORTED_CLASSES.add(Byte.class);
        SUPPORTED_CLASSES.add(Short.class);
        SUPPORTED_CLASSES.add(Integer.class);
        SUPPORTED_CLASSES.add(Long.class);
    }

    @Override
    protected boolean supported(Class<?> fieldClass) {
        return SUPPORTED_CLASSES.contains(fieldClass);
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		
		if(fieldValue == null) {
			return;
		}
		BigDecimal bigNum = new BigDecimal(fieldValue.toString()).stripTrailingZeros();
		int integerPartLength = bigNum.precision() - bigNum.scale();
		int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();
		
		DigitsConstraint digitsConstraint = (DigitsConstraint) extensionValue;
		
		int maxIntegerLength = digitsConstraint.getInteger();
		int maxFractionLength = digitsConstraint.getFraction();
		
		if(maxIntegerLength < integerPartLength || maxFractionLength < fractionPartLength) {
			throw new IllegalArgumentException("");
		}
	}

}