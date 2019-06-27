package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.HashSet;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;


/**
 * The annotated element must be true.
 * Supported types are {@code boolean} and {@code Boolean}.
 * <p>
 * {@code null} elements are considered valid.
 * s
 * {@see javax.validation.constraints.AssertTrue}
 * {@see javax.validation.constraints.AssertFalse}
 * 
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class AssertBoolValidator extends AbstractValidator {
    
    private static Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        SUPPORTED_CLASSES.add(Boolean.class);
    }
    
    @Override
    protected boolean supported(Class<?> fieldClass) {
        return SUPPORTED_CLASSES.contains(fieldClass);
    }
    
	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		// 数据不是目标类型，直接校验失败
		if(fieldValue == null) {
			return;
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
	    Set<Class<?>> SUPPORTED_CLASSES = new HashSet<Class<?>>();
	    SUPPORTED_CLASSES.add(Boolean.class);
	    System.out.println(SUPPORTED_CLASSES.contains(Boolean.class));
	}
}