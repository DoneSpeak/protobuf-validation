package cn.donespeak.protobufvalidation;

import java.util.Arrays;

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
				.setName("naneLL")
				.setAge(25)
				.setEmail("dfdfdfdf@gr.email.com")
				.setScore(22)
				.addAllRoles(Arrays.asList(1, 3))
				.setBalance(0)
				.build();
		
		validator.validate(user);
	}
}
