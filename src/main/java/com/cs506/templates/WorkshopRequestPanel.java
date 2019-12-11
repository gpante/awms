package com.cs506.templates;

import java.sql.Date;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.form.datetime.TimeField;
import org.apache.wicket.extensions.validation.validator.RfcCompliantEmailAddressValidator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.FormComponentFeedbackBorder;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.DateValidator;
import org.apache.wicket.validation.validator.StringValidator;
import com.cs506.WorkshopRequestSubmitted;
import com.cs506.database.Database;
import com.cs506.validator.PhoneNumberValidator;
import com.cs506.workshop.Area;
import com.cs506.workshop.WorkshopRequest;
import com.cs506.workshop.WorkshopType;

/**
 * Panel for a workshop request.
 * 
 * @author AJ
 */
public class WorkshopRequestPanel extends Panel {

	public WorkshopRequestPanel(String id) {
		super(id);
		add(new FeedbackPanel("feedback"));
		add(new WorkshopRequestForm("workshopRequestForm"));
	}
	
	public final class WorkshopRequestForm extends Form<WorkshopRequest> {

		public WorkshopRequestForm(String id) {
			super(id, new CompoundPropertyModel<>(new WorkshopRequest()));
			
			final TextField<String> nameOfGroup = new TextField<>("nameOfGroup");
			nameOfGroup.setRequired(true);
			nameOfGroup.add(new StringValidator(1, 30));
            final MarkupContainer nameOfGroupFeedback = new FormComponentFeedbackBorder("nameOfGroupFeedback");
            add(nameOfGroupFeedback);
            nameOfGroupFeedback.add(nameOfGroup);
			
			final TextField<String> nameOfContact = new TextField<>("nameOfContact");
			nameOfContact.setRequired(true);
			nameOfContact.add(new StringValidator(1, 30));
            final MarkupContainer nameOfContactFeedback = new FormComponentFeedbackBorder("nameOfContactFeedback");
            add(nameOfContactFeedback);
            nameOfContactFeedback.add(nameOfContact);

			RadioGroup<Boolean> consist75Group = new RadioGroup<>("consist75", new PropertyModel(getModel(), "consist75"));
			consist75Group.setRequired(true);
			consist75Group.add(new Radio<>("true", new Model<Boolean>(true)));
			consist75Group.add(new Radio<>("false", new Model<Boolean>(false)));
			add(consist75Group);
			
			final EmailTextField contactEmail = new EmailTextField("contactEmail");
			contactEmail.setRequired(true);
			contactEmail.add(RfcCompliantEmailAddressValidator.getInstance());
            final MarkupContainer contactEmailFeedback = new FormComponentFeedbackBorder("contactEmailFeedback");
            add(contactEmailFeedback);
            contactEmailFeedback.add(contactEmail);
			
			final TextField<String> contactPhone = new TextField<>("contactPhone");
			contactPhone.setRequired(true);
			contactPhone.add(new PhoneNumberValidator());
            final MarkupContainer contactPhoneFeedback = new FormComponentFeedbackBorder("contactPhoneFeedback");
            add(contactPhoneFeedback);
            contactPhoneFeedback.add(contactPhone);

			RadioGroup<WorkshopType> workshopTypeGroup = new RadioGroup<>("workshopType", new PropertyModel(getModel(), "workshopType"));
			workshopTypeGroup.setRequired(true);
			workshopTypeGroup.add(new Radio<>("high", new Model<WorkshopType>(WorkshopType.HIGH_ROPES_COURSE)));
			workshopTypeGroup.add(new Radio<>("low", new Model<WorkshopType>(WorkshopType.LOW_ROPES_COURSE)));
			workshopTypeGroup.add(new Radio<>("ground", new Model<WorkshopType>(WorkshopType.GROUND)));
			workshopTypeGroup.add(new Radio<>("unsure", new Model<WorkshopType>(WorkshopType.UNSURE)));
			add(workshopTypeGroup);
			
			final TextField<String> location = new TextField<>("location");
			location.setRequired(true);
			location.add(new StringValidator(1, 50));
            final MarkupContainer locationFeedback = new FormComponentFeedbackBorder("locationFeedback");
            add(locationFeedback);
            locationFeedback.add(location);
			
			final DateTextField date = new DateTextField("date");
			date.setRequired(true);
			date.add(DateValidator.range(Date.valueOf("2000-01-01"), Date.valueOf("3000-01-01")));
            final MarkupContainer dateFeedback = new FormComponentFeedbackBorder("dateFeedback");
            add(dateFeedback);
            dateFeedback.add(date);
			final TimeField startTime = new TimeField("startTime");
			startTime.setRequired(true);
            final MarkupContainer startTimeFeedback = new FormComponentFeedbackBorder("startTimeFeedback");
            add(startTimeFeedback);
            startTimeFeedback.add(startTime);
			final TimeField endTime = new TimeField("endTime");
			endTime.setRequired(true);
            final MarkupContainer endTimeFeedback = new FormComponentFeedbackBorder("endTimeFeedback");
            add(endTimeFeedback);
            endTimeFeedback.add(endTime);
			
			final DateTextField alternateDate = new DateTextField("alternateDate");
			alternateDate.setRequired(true);
			alternateDate.add(DateValidator.range(Date.valueOf("2000-01-01"), Date.valueOf("3000-01-01")));
            final MarkupContainer alternateDateFeedback = new FormComponentFeedbackBorder("alternateDateFeedback");
            add(alternateDateFeedback);
            alternateDateFeedback.add(alternateDate);
			final TimeField alternateStartTime = new TimeField("alternateStartTime");
			alternateStartTime.setRequired(true);
            final MarkupContainer alternateStartTimeFeedback = new FormComponentFeedbackBorder("alternateStartTimeFeedback");
            add(alternateStartTimeFeedback);
            alternateStartTimeFeedback.add(alternateStartTime);
			final TimeField alternateEndTime = new TimeField("alternateEndTime");
			alternateEndTime.setRequired(true);
            final MarkupContainer alternateEndTimeFeedback = new FormComponentFeedbackBorder("alternateEndTimeFeedback");
            add(alternateEndTimeFeedback);
            alternateEndTimeFeedback.add(alternateEndTime);
			
			final NumberTextField<Integer> participants = new NumberTextField<>("participants");
			participants.setRequired(true);
			participants.setMinimum(5);
            final MarkupContainer participantsFeedback = new FormComponentFeedbackBorder("participantsFeedback");
            add(participantsFeedback);
            participantsFeedback.add(participants);
            
            List<Area> list = Area.getAreas();
            CheckGroup areasGroup = new CheckGroup("areasGroup", new PropertyModel(getModel(), "areas"));
            ListView<Area> areas = new ListView<Area>("areas", list) {
				@Override
				protected void populateItem(ListItem<Area> item) {
					Check<Area> check = new Check<Area>("check", item.getModel());
					item.add(check);
					item.add(new Label("name", new PropertyModel(item.getModel(), "name")));
				}
            };
            
            areas.setReuseItems(true);
            
            areasGroup.add(areas);
            add(areasGroup);
			
			final TextField<String> howDidYouHear = new TextField<>("howDidYouHear");
			howDidYouHear.setRequired(true);
            final MarkupContainer howDidYouHearFeedback = new FormComponentFeedbackBorder("howDidYouHearFeedback");
            add(howDidYouHearFeedback);
            howDidYouHearFeedback.add(howDidYouHear);
			
			final TextField<String> special = new TextField<>("special");
            final MarkupContainer specialFeedback = new FormComponentFeedbackBorder("specialFeedback");
            add(specialFeedback);
            specialFeedback.add(special);
		}
		
		/**
		 * TODO send the request to the database.
		 */
		@Override
		public final void onSubmit() {
			WorkshopRequest request = getModelObject();
			System.out.println(request);
			
			Database db = new Database();
			try {	
	    		int resultValue = db.addWorkshop(request);
	    		if (resultValue > 0) {
	    			System.out.println("submitted");
	    		} else {
	    			System.out.println("submisson failed");
	    		}
	    	}catch(Exception e) {
	    		System.out.print(e);
	    		System.out.println("error");
	    	}finally {
	    		db.closeConn();
	    		System.out.println("db conn closed");
	    	}
			
			setResponsePage(WorkshopRequestSubmitted.class);
		}
		
	}

}
