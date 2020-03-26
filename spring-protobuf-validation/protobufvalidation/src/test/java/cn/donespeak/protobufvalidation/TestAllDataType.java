package cn.donespeak.protobufvalidation;

import java.util.HashMap;

import testoption.AllDataType;

public class TestAllDataType {

	/*
	 message AllDataType {
	
		option (testoption.message_option) = "1dfdf";
	
		map<string, int32> mapV = 1;
		repeated string list = 2;
	
		int32 intV = 3;
	 }
	 */
	public static void main(String[] args) {
		AllDataType.newBuilder()
			.putMapV("key", 3)
			.putAllMapV(new HashMap<String, Integer>())
			.addList("value");
	}
}
