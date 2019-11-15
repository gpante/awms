package com.cs506;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.cs506.templates.Base;
import com.cs506.templates.WorkshopRequestPanel;

/**
 * Workshop request page with the form attached.
 * TODO make the HTML file.
 * @author AJ
 */
public class WorkshopRequestPage extends Base {

	public WorkshopRequestPage(final PageParameters pageParameters) {
		super(pageParameters);
		add(new WorkshopRequestPanel("workshopRequestPanel"));
	}

}
