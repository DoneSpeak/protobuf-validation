package cn.donespeak.protobufvalidation.constraintvalidators;

import java.math.BigDecimal;
import java.math.BigInteger;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.RangeConstraint;
/**
 * The annotated element has to be in the appropriate range. Apply on numeric fieldValues or string
 * representation of the numeric fieldValue.
 * 
 * {@see org.hibernate.validator.constraints.Range}
 */
public class RangeValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		
		if(fieldValue == null) {
			return;
		}
		
		RangeConstraint rangeConstraint = (RangeConstraint) extensionValue;
		long max = rangeConstraint.hasMax()? rangeConstraint.getMax(): Long.MAX_VALUE;
		long min = rangeConstraint.hasMin()? rangeConstraint.getMin(): 0;
		
		boolean invalid = true;
		if(fieldValue instanceof BigDecimal) {
			BigDecimal fieldBigDecimalValue = (BigDecimal) fieldValue;
			invalid = fieldBigDecimalValue.compareTo(new BigDecimal(max)) > 0
				|| fieldBigDecimalValue.compareTo(new BigDecimal(min)) < 0;
			
		} else if(fieldValue instanceof BigInteger) {
			BigInteger fieldBigIntegerValue = (BigInteger) fieldValue;
			invalid = fieldBigIntegerValue.compareTo(BigInteger.valueOf(max)) > 0
				|| fieldBigIntegerValue.compareTo(BigInteger.valueOf(min)) < 0;	
			
		} else if(fieldValue instanceof Byte) {
			byte fieldByteValue = (Byte) fieldValue;
			invalid = fieldByteValue > max || fieldByteValue < min;
			
		} else if(fieldValue instanceof Short) {
			short shortValue = (Short) fieldValue;
			invalid = shortValue > max || shortValue < min;
			
		} else if(fieldValue instanceof Integer) {
			int intValue = (Integer) fieldValue;
			invalid = intValue > max || intValue < min;
			
		} else if(fieldValue instanceof Long) {
			long longValue = (Long) fieldValue;
			invalid = longValue > max || longValue < min;
			
		} else if (fieldValue instanceof Float) {			
			float floatValue = (Float) fieldValue;
			invalid = floatValue > max || floatValue < min;
		}
		if(invalid) {
			throw new IllegalArgumentException("RangeValidator");
		}
	}
}
