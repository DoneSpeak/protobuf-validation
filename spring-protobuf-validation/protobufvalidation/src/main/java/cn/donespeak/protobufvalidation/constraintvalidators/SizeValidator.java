package cn.donespeak.protobufvalidation.constraintvalidators;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.SizeContraint;

public class SizeValidator extends AbstractValidator {

	private final String message = "The size of the collection should be between " + " and " + "";
	private final String message1 = "The size of the collection should be greater than ";
	private final String message2 = "The size of the collection should be lower than ";
	
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			return;
		}
		int size = 0;
		if (fieldValue instanceof CharSequence) {
			size = ((CharSequence) fieldValue).length();
		} else if (fieldValue instanceof Collection) {
	        size = ((Collection<?>) fieldValue).size();
		} else if(fieldValue instanceof Map) {
			size = ((Map<?, ?>) fieldValue).size();
		} else if (fieldValue.getClass().isArray()) {
			size = Array.getLength(fieldValue);
		}
		
		checkArgument(size, extensionValue);
	}

	private void checkArgument(int size, Object extensionValue) {
		SizeContraint constraint = (SizeContraint) extensionValue;
		if(constraint.hasMin() && constraint.hasMax()) {
			boolean expression = constraint.getMin() <= size && size <= constraint.getMax();
			checkArgument(expression, message, constraint.getMin(), constraint.getMax());
		} else if (constraint.hasMin()) {
			checkArgument(constraint.getMin() <= size,  message1, constraint.getMin());			
		} else if (constraint.hasMax()) {
			checkArgument(size <= constraint.getMax(), message2, constraint.getMax());
		}
	}
	
	private void checkArgument(boolean expression, String message, Object ... args) {
		// TODO Auto-generated method stub
		
	}
}