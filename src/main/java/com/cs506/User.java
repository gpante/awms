package com.cs506;

import org.apache.wicket.util.io.IClusterable;

public final class User implements IClusterable {
	
	private final String username;

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
}
