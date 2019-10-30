package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public final class SignIn extends DefaultPage {

	public SignIn() {
		this(null);
	}

	public SignIn(PageParameters pageParameters) {
        super(pageParameters);
        add(new SignInPanel("signInPanel"));
	}

}
