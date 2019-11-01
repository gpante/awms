package com.cs506.workshop;

import java.util.Date;

/**
 * Workshop Request POJO
 * @author AJ
 */
public final class WorkshopRequest {
	
	private String nameOfGroup;
	private String nameOfContact;
	private boolean consist75;
	private String contactEmail;
	private String contactPhone;
	private WorkshopType workshopType;
	private String location;
	private Date date;
	private Date startTime;
	private Date endTime;
	private Date alternateDate;
	private Date alternateStartTime;
	private Date alternateEndTime;
	private int participants;
	private int areas;
	private String howDidYouHear;
	private String special;

	public WorkshopRequest() {
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getAlternateDate() {
		return alternateDate;
	}

	public void setAlternateDate(Date alternateDate) {
		this.alternateDate = alternateDate;
	}

	public Date getAlternateStartTime() {
		return alternateStartTime;
	}

	public void setAlternateStartTime(Date alternateStartTime) {
		this.alternateStartTime = alternateStartTime;
	}

	public Date getAlternateEndTime() {
		return alternateEndTime;
	}

	public void setAlternateEndTime(Date alternateEndTime) {
		this.alternateEndTime = alternateEndTime;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	public int getAreas() {
		return areas;
	}

	public void setAreas(int areas) {
		this.areas = areas;
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

}
