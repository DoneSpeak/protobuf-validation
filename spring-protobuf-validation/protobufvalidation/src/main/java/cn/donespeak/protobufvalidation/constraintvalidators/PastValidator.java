package cn.donespeak.protobufvalidation.constraintvalidators;

import cn.donespeak.protobufvalidation.AbstractValidator;

/**
 * 作用于long类型，则表示毫秒为单位的时间
 * 作用于int类型，则表示秒为单位的时间
 * @author yangguanrong
 *
 */
public class PastValidator extends AbstractValidator {

	@Override
	protected void doValidate(Object fieldValue, Object extensionValue, String errInfo)
			throws IllegalArgumentException {
		if(fieldValue == null) {
			return;
		}
		long now = System.currentTimeMillis();
		long timeMillis = 0;
		if(fieldValue instanceof Long) {
			timeMillis = (Long) fieldValue;
		} else if (fieldValue instanceof Integer) {
			timeMillis = 1000L * (Integer) fieldValue;
		}
		if(timeMillis >= now) {
			throw new IllegalArgumentException("");
		}
	}
}
