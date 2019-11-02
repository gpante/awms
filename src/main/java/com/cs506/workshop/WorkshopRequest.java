package com.cs506.workshop;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.wicket.util.io.IClusterable;

/**
 * Workshop Request POJO
 * @author AJ
 */
public final class WorkshopRequest implements IClusterable {
	
	private String nameOfGroup;
	private String nameOfContact;
	private boolean consist75;
	private String contactEmail;
	private String contactPhone;
	private WorkshopType workshopType;
	private String location;
	private Date date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Date alternateDate;
	private LocalTime alternateStartTime;
	private LocalTime alternateEndTime;
	private int participants;
	private List<Area> areas;
	private int areasValue;
	private String howDidYouHear;
	private String special;
	
	public String toString() {
		return "WorkshopRequest:" +
				"\n\tnameOfGroup: " + nameOfGroup +
				"\n\tnameOfContact: " + nameOfContact +
				"\n\tconsist75: " + consist75 +
				"\n\tcontactEmail: " + contactEmail +
				"\n\tcontactPhone: " + contactPhone +
				"\n\tworkshopType: " + workshopType +
				"\n\tlocation: " + location +
				"\n\tdate: " + date +
				"\n\tstartTime: " + startTime +
				"\n\tendTime: " + endTime +
				"\n\talternateDate: " + alternateDate +
				"\n\talternateStartTime: " + alternateStartTime +
				"\n\talternateEndTime: " + alternateEndTime +
				"\n\tparticipants: " + participants +
				"\n\tareas: " + areas +
				"\n\tareasValue: " + areasValue +
				"\n\thowDidYouHear: " + howDidYouHear +
				"\n\tspecial: " + special;
	}

	public WorkshopRequest() {
		this.areas = new ArrayList<Area>();
	}

	public String getNameOfGroup() {
		return nameOfGroup;
	}

	public void setNameOfGroup(String nameOfGroup) {
		this.nameOfGroup = nameOfGroup;
	}

	public String getNameOfContact() {
		return nameOfContact;
	}

	public void setNameOfContact(String nameOfContact) {
		this.nameOfContact = nameOfContact;
	}

	public boolean isConsist75() {
		return consist75;
	}

	public void setConsist75(boolean consist75) {
		this.consist75 = consist75;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public WorkshopType getWorkshopType() {
		return workshopType;
	}

	public void setWorkshopType(WorkshopType workshopType) {
		this.workshopType = workshopType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Date getAlternateDate() {
		return alternateDate;
	}

	public void setAlternateDate(Date alternateDate) {
		this.alternateDate = alternateDate;
	}

	public LocalTime getAlternateStartTime() {
		return alternateStartTime;
	}

	public void setAlternateStartTime(LocalTime alternateStartTime) {
		this.alternateStartTime = alternateStartTime;
	}

	public LocalTime getAlternateEndTime() {
		return alternateEndTime;
	}

	public void setAlternateEndTime(LocalTime alternateEndTime) {
		this.alternateEndTime = alternateEndTime;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
		this.setAreasValue(0);
		for (Area a : areas)
			this.setAreasValue(this.getAreasValue() + a.getValue());
	}

	public String getHowDidYouHear() {
		return howDidYouHear;
	}

	public void setHowDidYouHear(String howDidYouHear) {
		this.howDidYouHear = howDidYouHear;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public int getAreasValue() {
		return areasValue;
	}

	private void setAreasValue(int areasValue) {
		this.areasValue = areasValue;
	}

}
