package com.cs506.templates;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Panel for a workshop request.
 * TODO make the HTML file.
 * @author AJ
 */
public class WorkshopRequestPanel extends Panel {

	public WorkshopRequestPanel(String id) {
		super(id);
		add(new FeedbackPanel("feedback"));
		add(new WorkshopRequestForm("workshopRequestForm"));
	}
	
	public final class WorkshopRequestForm extends StatelessForm<WorkshopRequestPanel> {

		public WorkshopRequestForm(String id) {
			super(id);
		}
		
		/**
		 * TODO move input into a {@code WorkshopRequest} object.
		 * TODO send the request to the database.
		 */
		@Override
		public final void onSubmit() {
			
		}
		
	}

}
