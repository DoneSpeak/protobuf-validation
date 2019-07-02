package cn.donespeak.protobufvalidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.donespeak.exchange.UserProto;
import cn.donespeak.exchange.UserProto.ActiveStatusE;
import cn.donespeak.exchange.UserProto.Address;

public class MessageFieldValueTest {

	public static void main(String[] args) {
		UserProto.UserRequest user = UserProto.UserRequest.newBuilder()
				.setName("naneLL")
				.setAge(25)
				.setEmail("dfdfdfdf@gr.email.com")
				.setScore(22)
				.setBalance(0)
				.setActiveStatueValue(-10)
				.build();
		
		System.out.println("rolesList: " + user.getRolesList());
		System.out.println("address: " + user.getAddress().toString());
		System.out.println("address == null? : " + (user.getAddress() == null));
		System.out.println("address.subAddress: " + user.getAddress().getSubAddress().getSubAddress());
		
		Address address = user.getAddress();
		Address subAddress = address.getSubAddress();
		// return address_ == null ? cn.donespeak.exchange.UserProto.Address.getDefaultInstance() : address_;
		System.out.println("address == subAddress: " + (address == subAddress));
		
		System.out.println("activeStatus: " + user.getActiveStatue());
		System.out.println("activeStatusValue: " + user.getActiveStatueValue());
		
		System.out.println("-1 activeStatus: " + ActiveStatusE.forNumber(88));
		
		// 对builder的修改不会影响到原本的user
		user.toBuilder().addAllRoles(new ArrayList<Integer>(Arrays.asList(1, 2,3)));
		
		System.out.println("addRoles-1: " + user.getRolesList());
		
		// 如果需要修改一个对象的值，则需要通过builder生成的对象覆盖原来的对象
		// 从这层意义上来讲，所有的proto java对象都是不可修改对象。
		user = user.toBuilder().addAllRoles(new ArrayList<Integer>(Arrays.asList(1, 2,3))).build();
		
		System.out.println("addRoles-2: " + user.getRolesList());
		
		List<Integer> roles = user.getRolesList();
		System.out.println("roles: " + roles);
	}
}
