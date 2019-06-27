package cn.donespeak.protobufvalidation.constraintvalidators;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.NumberUtil;
/**
 * The annotated element must be a strictly positive number (i.e. 0 is considered as an
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
 *
 * {@see javax.validation.constraints.Positive}
 *
 * @author guanrong yang
 */
public class PositiveValidator extends AbstractValidator {
    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        SUPPORTED_CLASSES.add(BigDecimal.class);
        SUPPORTED_CLASSES.add(BigInteger.class);
        // number type
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
		    // null值通过校验
			return;
		}
		
		Boolean isPositive = NumberUtil.isPossitive(fieldValue);
		
		if(isPositive == false) {
			throw new IllegalArgumentException("");
		}
	}
}
