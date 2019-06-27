package cn.donespeak.protobufvalidation.constraintvalidators;

import java.math.BigDecimal;

import cn.donespeak.protobufvalidation.AbstractValidator;
import valid.DigitsConstraint;

public class DigitsValidator extends AbstractValidator {

	private final String message = "";
	
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		
		if(fieldValue == null) {
			return;
		}
		BigDecimal bigNum = new BigDecimal(fieldValue.toString()).stripTrailingZeros();
		int integerPartLength = bigNum.precision() - bigNum.scale();
		int fractionPartLength = bigNum.scale() < 0 ? 0 : bigNum.scale();
		
		DigitsConstraint digitsConstraint = (DigitsConstraint) extensionValue;
		
		int maxIntegerLength = digitsConstraint.getInteger();
		int maxFractionLength = digitsConstraint.getFraction();
		
		if(maxIntegerLength < integerPartLength || maxFractionLength < fractionPartLength) {
			throw new IllegalArgumentException("");
		}
	}

    @Override
    protected void supported(Object fieldValue) throws IllegalArgumentException {
        // TODO Auto-generated method stub
         
    }
}