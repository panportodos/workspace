package com.study.insert_and_select1.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
	private int StudentId;
	private String name;
	private int age;
}
