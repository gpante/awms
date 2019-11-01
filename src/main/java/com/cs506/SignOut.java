package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.cs506.templates.Base;

public final class SignOut extends Base {
    public SignOut(final PageParameters parameters) {
        getSession().invalidate();
    }
}