package com.cs506;

import java.util.LinkedList;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.cs506.database.Database;
import com.cs506.templates.Base;


public class WorkshopTable extends Base {
	private static final long serialVersionUID = 1L;

	public WorkshopTable(final PageParameters parameters) {
		super();

		try {
			Database db = new Database();
			LinkedList<String[]> workshops = db.getAllWorkshops();
			
			add(new ListView<String[]>("workshops", workshops) {
                @Override
                protected void populateItem(ListItem<String[]> item) {
                    final Link<Void> workshopLink = new Link<Void>("link") {

            				@Override
            				public void onClick() {
            					setResponsePage(new WorkshopEditPage(new Database().getWorkshop(item.getModel().getObject()[0])));
            				}
        				
        				};
                        	workshopLink.add(new Label("groupNameText", new PropertyModel(item.getModel(), "0")));
                        	item.add(workshopLink);
                           item.add(new Label("contactName", new PropertyModel(item.getModel(), "1")));
                           item.add(new Label("consist75", new PropertyModel(item.getModel(), "2")));
                           item.add(new Label("email", new PropertyModel(item.getModel(), "3")));
                           item.add(new Label("phone", new PropertyModel(item.getModel(), "4")));
                           item.add(new Label("workshopType", new PropertyModel(item.getModel(), "5")));
                           item.add(new Label("location", new PropertyModel(item.getModel(), "6")));
                           item.add(new Label("date", new PropertyModel(item.getModel(), "7")));
                           item.add(new Label("startTime", new PropertyModel(item.getModel(), "8")));
                           item.add(new Label("endTime", new PropertyModel(item.getModel(), "9")));
                           item.add(new Label("altDate", new PropertyModel(item.getModel(), "10")));
                           item.add(new Label("altStart", new PropertyModel(item.getModel(), "11")));
                           item.add(new Label("altEnd", new PropertyModel(item.getModel(), "12")));
                           item.add(new Label("participants", new PropertyModel(item.getModel(), "13")));
                           item.add(new Label("areas", new PropertyModel(item.getModel(), "14")));
                           item.add(new Label("howHeard", new PropertyModel(item.getModel(), "15")));
                           item.add(new Label("special", new PropertyModel(item.getModel(), "16")));   
                }
			});
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}