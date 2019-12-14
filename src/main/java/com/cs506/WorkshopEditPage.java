package com.cs506;

import org.apache.wicket.util.io.IClusterable;

import com.cs506.templates.AuthenticatedBase;
import com.cs506.templates.WorkshopEditPanel;
import com.cs506.workshop.WorkshopRequest;

/**
 * Workshop edit page with the form attached.
 * @author AJ
 */
public final class WorkshopEditPage extends AuthenticatedBase implements IClusterable {

	public WorkshopEditPage(final WorkshopRequest request) {
		add(new WorkshopEditPanel("workshopEditPanel", request));
	}

}
