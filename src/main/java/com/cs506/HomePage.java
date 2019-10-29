package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import com.cs506.templates.Base;
import org.apache.wicket.markup.html.basic.Label;


public class HomePage extends Base {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super();

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}
}
