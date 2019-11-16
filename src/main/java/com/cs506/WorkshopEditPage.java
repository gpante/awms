package com.cs506;

import com.cs506.templates.Base;
import com.cs506.templates.WorkshopEditPanel;
import com.cs506.workshop.WorkshopRequest;

/**
 * Workshop edit page with the form attached.
 * @author AJ
 */
public class WorkshopEditPage extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -241045865922862107L;

	public WorkshopEditPage(final WorkshopRequest request) {
		add(new WorkshopEditPanel("workshopEditPanel", request));
	}

}
