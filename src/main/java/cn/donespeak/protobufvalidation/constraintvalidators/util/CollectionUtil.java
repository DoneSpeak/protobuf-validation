package cn.donespeak.protobufvalidation.constraintvalidators.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CollectionUtil {

	public static Integer getSize(Object value) {

		if (value instanceof CharSequence) {
			return ((CharSequence) value).length();
		} else if (value instanceof Collection) {
	        return ((Collection<?>) value).size();
		} else if(value instanceof Map) {
			return ((Map<?, ?>) value).size();
		} else if (value.getClass().isArray()) {
			return Array.getLength(value);
		} else {
			return null;
		}
	}
}
