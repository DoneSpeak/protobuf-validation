package cn.donespeak.protobufvalidation;

import org.junit.Before;
import org.junit.Test;

import cn.donespeak.protobufvalidation.interceptor.AbstractValidationInterceptor;

public class ValidationInterceptorTest {

	private AbstractValidationInterceptor validator;
	
	
	@Before
	public void before() {
		validator = new AbstractValidationInterceptor() {
			
		};
	}
	
	@Test
	public void testValidate() {
//		UserRequest.UserProto userProto = UserRequest.UserProto.newBuilder()
//				.setEmail("gr@gr.com")
//				.setName("Gr")
//				.setPhone("125487451521")
//				.build()
//				;
	}
}
