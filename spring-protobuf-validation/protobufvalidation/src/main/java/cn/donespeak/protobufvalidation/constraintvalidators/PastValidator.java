package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated element must be an instant, date or time in the past.
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code int}</li>
 *     <li>{@code long}</li>
 * </ul>
 * 
 * Data of type {@code int} is expressed in seconds. 
 * And data of type {@code long} is expressed in milliseconds.
 * <p>
 * {@code null} elements are considered valid.
 * 
 * {@see javax.validation.constraints.Future}
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class PastValidator extends AbstractValidator {
    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
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
		long now = System.currentTimeMillis();
		long timeMillis = 0;
		if(fieldValue instanceof Long) {
			timeMillis = (Long) fieldValue;
		} else if (fieldValue instanceof Integer) {
			timeMillis = 1000L * (Integer) fieldValue;
		}
		if(timeMillis >= now) {
			throw new IllegalArgumentException("");
		}
	}
}
