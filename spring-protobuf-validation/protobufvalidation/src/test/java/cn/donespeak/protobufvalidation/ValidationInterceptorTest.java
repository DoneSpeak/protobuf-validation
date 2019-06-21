package cn.donespeak.protobufvalidation;

import org.junit.Before;
import org.junit.Test;

import com.google.protobuf.GeneratedMessageV3;

import cn.donespeak.exchange.UserProto;
import cn.donespeak.protobufvalidation.interceptor.AbstractValidationInterceptor;

public class ValidationInterceptorTest {
	
	public class ValidationInterceptor extends AbstractValidationInterceptor {
		
		@Override
		public void validate(GeneratedMessageV3 messageV3) {
			super.validate(messageV3);
		}
	}

	private ValidationInterceptor validator;
		
	@Before
	public void before() {
		validator = new ValidationInterceptor();
	}
	
	@Test
	public void testValidate() {
		UserProto.UserRequest user = UserProto.UserRequest.newBuilder()
				.setAge(1000)
				.setName("nane")
				.build();
		
		validator.validate(user);
	}
}
