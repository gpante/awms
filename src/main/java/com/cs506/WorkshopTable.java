package com.cs506;

import java.util.LinkedList;
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
			System.out.println(workshops.getFirst());
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}