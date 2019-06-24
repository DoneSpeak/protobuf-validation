package cn.donespeak.protobufvalidation.constraintvalidators;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

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
 * @author guanrong.yang
 */
public class NotEmptyValidator extends AbstractValidator {

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
