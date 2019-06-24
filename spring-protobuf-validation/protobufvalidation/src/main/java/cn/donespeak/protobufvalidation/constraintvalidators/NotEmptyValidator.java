package cn.donespeak.protobufvalidation.constraintvalidators;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * 不可以为 null 或者 empty
 * 作用于 集合类。
 * @author yangguanrong
 *
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
