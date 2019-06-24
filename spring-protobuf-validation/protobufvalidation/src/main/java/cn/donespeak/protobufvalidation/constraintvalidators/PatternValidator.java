package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.regex.Pattern;

import cn.donespeak.protobufvalidation.AbstractValidator;

public class PatternValidator  extends AbstractValidator {
	
    @Override
    protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
    		throws IllegalArgumentException {
    	if(fieldValue == null) {
    		return;
    	}
        String extensionValueStr = extensionValue.toString();
        String fieldValueStr = fieldValue.toString();

        Pattern pattern = Pattern.compile(extensionValueStr);
        if(!pattern.matcher(fieldValueStr).matches()) {
        	throw new IllegalArgumentException("");
        }
    }
}
