package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.CollectionUtil;
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
		Integer size = CollectionUtil.getSize(fieldValue);
		if(size == null) {
			return;
		}
		checkArgument(size, (SizeContraint) extensionValue);
	}

	private void checkArgument(int size, SizeContraint constraint) {
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