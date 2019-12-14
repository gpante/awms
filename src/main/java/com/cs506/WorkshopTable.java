package com.cs506;

import java.util.LinkedList;
import java.util.ListIterator;

import org.apache.wicket.Session;
import org.apache.wicket.extensions.ajax.markup.html.AjaxEditableLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.io.IClusterable;

import com.cs506.database.Database;
import com.cs506.templates.AuthenticatedBase;


public final class WorkshopTable extends AuthenticatedBase implements IClusterable {

	public WorkshopTable(final PageParameters parameters) {
		super();
		
		Database db = new Database();
		
		try {
			
			LinkedList<String[]> workshops = db.getAllWorkshops();
			
			add(new ListView<String[]>("workshops", workshops) {

				@Override
                protected void populateItem(ListItem<String[]> item) {
                    
					String[] model = item.getModelObject();
					String username = ((SecureSession)Session.get()).getUser().getUsername();
					
					final Link<Void> workshopLink = new Link<Void>("link") {

            				@Override
            				public void onClick() {
            					setResponsePage(new WorkshopEditPage(db.getWorkshop(item.getModel().getObject()[0])));
            				}
        				
        			};
                    workshopLink.add(new Label("groupNameText", new PropertyModel<Object>(item.getModel(), "0")));
                    item.add(workshopLink);
                    
                    final Link<Void> a = new Link<Void>("linkA") {

						@Override
						public void onClick() {
							db.assignFacilitator(username, model[0]);
						}
					
                    };
                    a.add(new Label("signup", "Sign Up"));
                    item.add(a);
                    
                    item.add(new Label("contactName", new PropertyModel<String>(model, "1")));
                    item.add(new Label("consist75", new PropertyModel<String>(model, "2")));
                    item.add(new Label("email", new PropertyModel<String>(model, "3")));
                    item.add(new Label("phone", new PropertyModel<String>(model, "4")));
                    item.add(new Label("workshopType", new PropertyModel<String>(model, "5")));
                    item.add(new Label("location", new PropertyModel<String>(model, "6")));
                    item.add(new Label("date", new PropertyModel<String>(model, "7")));
                    item.add(new Label("startTime", new PropertyModel<String>(model, "8")));
                    item.add(new Label("endTime", new PropertyModel<String>(model, "9")));
                    item.add(new Label("altDate", new PropertyModel<String>(model, "10")));
                    item.add(new Label("altStart", new PropertyModel<String>(model, "11")));
                    item.add(new Label("altEnd", new PropertyModel<String>(model, "12")));
                    item.add(new Label("participants", new PropertyModel<String>(model, "13")));
                    item.add(new Label("areas", new PropertyModel<String>(model, "14")));
                    item.add(new Label("howHeard", new PropertyModel<String>(model, "15")));
                    //item.add(new Label("special", new PropertyModel<String>(model, "16")));
                    item.add(new AjaxEditableLabel("special", new PropertyModel(model, "16")));
                    
                    LinkedList<String[]> list = db.getFacilitators(model[0]);
                    ListIterator<String[]> iter = list.listIterator(0);
                    String facilitators = "";
                    while(iter.hasNext()) {
                    	String[] temp = (String[])iter.next();
                    	facilitators = facilitators.concat(temp[0] + "\n");
                    	System.out.println(facilitators);
                    }
                    System.out.println(facilitators);
                    item.add(new Label("facilitator", facilitators));
                }
			});
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//db.closeConn();
	}
}