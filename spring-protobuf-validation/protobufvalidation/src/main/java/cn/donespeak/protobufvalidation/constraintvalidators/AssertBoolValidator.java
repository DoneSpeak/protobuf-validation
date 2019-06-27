package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * null 值时通过校验
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class AssertBoolValidator extends AbstractValidator {

    @Override
    protected void supported(Object fieldValue) throws IllegalArgumentException {
        
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		// 数据不是目标类型，直接校验失败
		if(fieldValue == null || !(fieldValue instanceof Boolean)) {
			throw new IllegalArgumentException("");
		}
		Boolean fieldBoolValue = Boolean.valueOf(fieldValue.toString());
		Boolean extensionBoolValue = Boolean.valueOf(extensionValue.toString());
		
		if(fieldBoolValue.equals(extensionBoolValue)) {
		    throw new IllegalArgumentException("");
		}
	}

	public static boolean test(Object obj) {
	    return obj instanceof String;
	}
	
	public static void main(String[] args) {
	    String a = "";
	    System.out.println(test(a));
	}
}