package cn.donespeak.protobufvalidation;

import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cn.donespeak.exchange.UserProto;

public class ProtoValidatorImplTest {
	
	private ProtoValidatorImpl validator;
		
	@Before
	public void before() {
		validator = new ProtoValidatorImpl();
	}
	
	@Test
	public void testValidate() {
	    UserProto.House house = UserProto.House.newBuilder().setLocation("").build();
		UserProto.UserRequest user = UserProto.UserRequest.newBuilder()
				.setName("")
				.setAge(205)
				.setEmail("dfdfdfdgr.email.com")
				.setScore(22000)
				.addAllRoles(Arrays.asList(1, 3))
				.setBalance(-10)
				.setHouse(house)
				.build();

		Set<ProtoConstraintViolation> errors = validator.validate(user);
        System.out.println("errors: " + errors.size());
        
		for(ProtoConstraintViolation error: errors) {
		    System.out.println(error);
		}
	}
	
	public static void main(String[] args) {
	    ProtoValidatorImplTest test = new ProtoValidatorImplTest();
	    test.before();
	    test.testValidate();
	}
}
