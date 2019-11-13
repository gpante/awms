package com.cs506;

import java.util.LinkedList;

import org.apache.wicket.markup.html.list.ListView;
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
			for(int i=0;i<19;i++) {
				System.out.println(workshops.getFirst()[i]);
			}
			
//			add(new ListView<Person>("persons", persons) {
//                @Override
//                protected void populateItem(ListItem<Person> item) {
//                           item.add(new Label("fullName", new PropertyModel(item.getModel(), "fullName")));
//                }
//			});
			
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}