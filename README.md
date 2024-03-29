# Protobuf Validation

## 可选的protobuf validation

```protobuf
syntax = "proto2";

option java_multiple_files = true;

import "google/protobuf/descriptor.proto";

package valid;

extend google.protobuf.FieldOptions {
    // 为true时，不能为null，false 时必须为null
    optional bool not_null = 5101;
    // 检查 boolean 类型属性指定值是否匹配
    optional bool assert_bool = 5102;
    // 检查数值类型属性的值是否大于 min
    optional int64 min = 5103;
    // 检查数值类型属性的值是否小于 max
    optional int64 max = 5104;
    // 检查集合类大小
    optional SizeContraint size = 5105;
    // 检查数值 整数位和小数位 是否小于指定值
    optional DigitsConstraint digits = 5106;
    // 检查时间是否是当前时间之前，如果为 int32 类型，则表示单位为 秒，int64 则表示单位为 毫秒。
    // 当值为ture时，表示过去（不包含现在），false表示现在或者未来
    optional bool past = 5107;
    // 检查时间是否是当前时间之后，如果为 int32 类型，则表示单位为 秒，int64 则表示单位为 毫秒。
    // 当值为ture时，表示未来（不包含现在），false表示现在或者过去
    optional bool future = 5108;
    // 校验字符串是否符合正则表达式
    optional string pattern = 5109;
    // 为true时，字符串不能为空，且trim之后长度大于 0
    optional bool not_blank = 5110;
    // 为true时，集合不能为空，且大小必须大于 0
    optional bool not_empty = 5111;
    // 为true时，表示必须为正数，false时，则为非正数
    optional bool positive = 5112;
    // 为true时，表示必须为负数，false时，则为非负数
    optional bool negative = 5113;
    // 取值范围应该在range中
    optional RangeConstraint range = 5114;
    // 字符串长度应该在length指定的范围内
    optional LengthConstraint length = 5115;
    // 必须是电子邮箱地址
    optional bool email = 5116;
	// 校验 proto 类，为true时进行级联校验，false时不进行
    optional bool validate = 5117;
}

message DigitsConstraint {
	// 小数点前的数的位数
    optional int32 integer = 1;
    // 小数点后的数的位数
    optional int32 fraction = 2;
}

message SizeContraint {
	// 缺省值为 0
    optional int32 min = 1;
    // 缺省值为 Integer.MAX_VALUE
    optional int32 max = 2;
}

message RangeConstraint {
	// 缺省值为
    optional int64 min = 1;
    // 缺省值为 Long.MAX_VALUE
    optional int64 max = 2;
}

message LengthConstraint {
	// 缺省值为 0
    optional int32 min = 1;
    // 缺省值为 Integer.MAX_VALUE
    optional int32 max = 2;
}
```

一个sample：
```protobuf
syntax = "proto3";

package cn.donespeak.exchange;

import "protoValidation.proto";

message UserRequest {
	string name = 1 [(valid.pattern) = "[a-zA-Z]{5,20}"];
	int32 age = 2 [(valid.max) = 100, (valid.min) = 18];
	string email = 3 [(valid.not_null) = true, (valid.email) = true];
	int32 score = 4 [(valid.range) = {min: 0, max: 100}];
	repeated int32 roles = 5 [(valid.not_empty) = true];
	int32 balance = 6 [(valid.negative) = false];

	ActiveStatusE active_statue = 7;

	Address address = 8 [(valid.validate) = false];

	House house = 9 [(valid.validate) = true];
}

message Address {
	string city = 1 [(valid.not_blank) = true];
	string state = 2 [(valid.not_blank) = true];

	Address sub_address = 3;
}

message House {
	string location = 1 [(valid.not_blank) = true];
	Address address = 2 [(valid.validate) = true];
}

enum ActiveStatusE {
	AS_UNRECOGNIZED = 0;
	ACTIVED = 1;
	DELETED = 2;
	LOCKED = 3;
	INACTIVE = -1;
}
```

## 开发说明

