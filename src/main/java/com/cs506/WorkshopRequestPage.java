package com.cs506;

import com.cs506.templates.AuthenticatedBase;
import com.cs506.templates.WorkshopRequestPanel;

/**
 * Workshop request page with the form attached.
 * TODO make the HTML file.
 * @author AJ
 */
public class WorkshopRequestPage extends AuthenticatedBase {

	public WorkshopRequestPage() {
		add(new WorkshopRequestPanel("workshopRequestPanel"));
	}

}
