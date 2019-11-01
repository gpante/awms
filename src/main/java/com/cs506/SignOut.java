package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.cs506.templates.AuthenticatedBase;

public final class SignOut extends AuthenticatedBase {
	
    public SignOut(final PageParameters parameters) {
        getSession().invalidate();
    }
}