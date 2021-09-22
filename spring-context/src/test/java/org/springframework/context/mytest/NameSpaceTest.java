package org.springframework.context.mytest;

import org.springframework.stereotype.Component;

@Component
public class NameSpaceTest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
