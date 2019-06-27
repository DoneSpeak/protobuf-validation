package cn.donespeak.protobufvalidation.constraintvalidators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.donespeak.protobufvalidation.AbstractValidator;
import cn.donespeak.protobufvalidation.constraintvalidators.util.CollectionUtil;
import valid.SizeContraint;

/**
 * The annotated element size must be between the specified boundaries (included).
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code CharSequence} (length of character sequence is evaluated)</li>
 *     <li>{@code Collection} (collection size is evaluated)</li>
 *     <li>{@code Map} (map size is evaluated)</li>
 *     <li>Array (array length is evaluated)</li>
 * </ul>
 * <p>
 * {@code null} elements are considered valid.
 *
 * @author Guanrong Yang
 * @date 2019/06/27
 */
public class SizeValidator extends AbstractValidator {

	private static Set<Class<?>> BASE_SUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        BASE_SUPPORTED_CLASSES.add(CharSequence.class);
        BASE_SUPPORTED_CLASSES.add(Collection.class);
        BASE_SUPPORTED_CLASSES.add(Map.class);
    }

    @Override
    protected boolean supported(Class<?> fieldClass) {
        return BASE_SUPPORTED_CLASSES.contains(fieldClass) || fieldClass.isArray();
    }
    
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
	    

        int max = constraint.hasMax()? constraint.getMax(): Integer.MAX_VALUE;
        int min = constraint.hasMin()? constraint.getMin(): 0;

        if(size > max || size < min) {
            throw new IllegalArgumentException("The size of the collection should be between " + min + " and " + max);
        }
	}
}