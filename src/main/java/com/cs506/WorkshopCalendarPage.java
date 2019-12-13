package com.cs506;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

import com.cs506.templates.Base;

public class WorkshopCalendarPage extends Base {

	public WorkshopCalendarPage() {
		add(new WebMarkupContainer("iframe") {
			   protected final void onComponentTag(ComponentTag tag) {
				   checkComponentTag(tag, "iframe");
				   tag.put("src", "https://calendar.google.com/calendar/embed?src=adventurelearningprograms0%40gmail.com&ctz=America%2FChicago");
				   tag.put("style", "border: 0");
				   tag.put("width", "1280");
				   tag.put("height", "720");
				   tag.put("frameborder", "0");
				   tag.put("scrolling", "no");
				   super.onComponentTag(tag);
			   }
			});
	}

}
