package com.cs506;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class DefaultPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public DefaultPage() {
		this(new PageParameters());
	}

	public DefaultPage(final PageParameters pageParameters) {
		super(pageParameters);
		
		if (((SecureSession)getSession()).getUser() != null && !this.getClass().equals(SignOut.class)) {
			final BookmarkablePageLink<Void> signInLink = new BookmarkablePageLink<>("logg", SignOut.class);
			signInLink.add(new Label("logtext", "Sign Out"));
			add(signInLink);
		} else {
			final BookmarkablePageLink<Void> signInLink = new BookmarkablePageLink<>("logg", SignIn.class);
			signInLink.add(new Label("logtext", "Sign In"));
			add(signInLink);
		}
	}


	public DefaultPage(IModel<?> model)
	{
		super(model);
	}
}