package cn.donespeak.protobufvalidation.constraintvalidators;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * The annotated element must not be {@code null} nor empty. Supported types are:
 * <ul>
 * <li>{@code CharSequence} (length of character sequence is evaluated)</li>
 * <li>{@code Collection} (collection size is evaluated)</li>
 * <li>{@code Map} (map size is evaluated)</li>
 * <li>Array (array length is evaluated)</li>
 * </ul>
 * 
 * {@see javax.validation.constraints.NotEmpty}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class NotEmptyValidator extends AbstractValidator {
    private static Set<Class<?>> BASE_SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        BASE_SUPPORTED_CLASSES.add(CharSequence.class);
        BASE_SUPPORTED_CLASSES.add(Collection.class);
        BASE_SUPPORTED_CLASSES.add(Map.class);
    }

    @Override
    protected boolean supported(Class<?> fieldClass) {
        return BASE_SUPPORTED_CLASSES.contains(fieldClass) || fieldClass.isArray();
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		boolean isEmpty = false;
		if(fieldValue == null) {
			isEmpty = true;
		} else if (fieldValue instanceof CharSequence) {
			isEmpty = ((CharSequence) fieldValue).length() == 0;
		} else if (fieldValue instanceof Collection) {
			isEmpty = ((Collection<?>) fieldValue).isEmpty();
		} else if(fieldValue instanceof Map) {
			isEmpty = ((Map<?, ?>) fieldValue).isEmpty();
		} else if (fieldValue.getClass().isArray()) {
			isEmpty = Array.getLength(fieldValue) == 0;
		}
		
		boolean isNotEmptyRequired = (Boolean) extensionValue;
		
		if(isEmpty == isNotEmptyRequired) {
			throw new IllegalArgumentException("NotEmptyValidator");
		}
	}
}
