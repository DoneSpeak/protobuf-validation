package cn.donespeak.protobufvalidation.constraintvalidators.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtil {

	public static Boolean isPossitive(Object value) {
		
		if(value instanceof BigDecimal) {
			return ((BigDecimal) value).compareTo(BigDecimal.ZERO) > 0;
		} else if(value instanceof BigInteger) {
			return ((BigInteger) value).compareTo(BigInteger.ZERO) > 0;
		} else if(value instanceof Byte) {
			return (Byte) value > 0;
		} else if(value instanceof Short) {
			return (Short) value > 0;
		} else if(value instanceof Integer) {
			return (Integer) value > 0;
		} else if(value instanceof Long) {
			return (Long) value > 0;
		} else if (value instanceof Float) {
			return (Float) value > 0;
		}
		return null;
	}
}
