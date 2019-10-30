package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public final class SignOut extends DefaultPage {
    public SignOut(final PageParameters parameters) {
        getSession().invalidate();
    }
}