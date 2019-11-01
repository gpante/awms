package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import com.cs506.templates.Base;
import org.apache.wicket.markup.html.basic.Label;


public class AboutALPs extends Base {
	private static final long serialVersionUID = 1L;

	public AboutALPs(final PageParameters parameters) {
		super();
		System.out.println(parameters);
		//add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

	}
}

