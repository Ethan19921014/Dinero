package model;

import java.time.LocalDate;
import java.util.Date;


/**
 * Todo.java
 * This is a model class represents a Todo entity
 * @author Ramesh Fadatare
 *
 */
public class Profiles {

	private long ProfileID;
	private String gender;
	private String Email;
	private String cellphone;
//	private LocalDate birthday;
	private String address;
	
	protected Profiles() {
		
	}
	
	public  Profiles( long ProfileID, String gender, String Email, String cellphone, String address) {
		super();
		this.ProfileID = ProfileID;
		this.gender = gender;
		this.Email = Email;
		this.cellphone = cellphone;
//		this.birthday = birthday;
		this.address = address;
	}
	

	public Profiles( String gender, String Email, String cellphone, String address) {
		super();
		this.gender = gender;
		this.Email = Email;
		this.cellphone = cellphone;
//		this.birthday = birthday;
		this.address = address;
	}

	/*
	 * public Todo(String title, String username, String description, LocalDate
	 * targetDate, boolean isDone) { super(); this.title = title; this.username =
	 * username; this.description = description; this.targetDate = targetDate;
	 * this.status = isDone; }
	 */
	

	public long getProfileID() {
	return ProfileID;
}

public void setProfileID(long profileID) {
	this.ProfileID = profileID;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getEmail() {
	return Email;
}

public void setEmail(String email) {
	Email = email;
}

public String getCellphone() {
	return cellphone;
}

public void setCellphone(String cellphone) {
	this.cellphone = cellphone;
}

//public LocalDate getBirthday() {
//	return birthday;
//}
//
//public void setBirthday(LocalDate birthday) {
//	this.birthday = birthday;
//}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ProfileID ^ (ProfileID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profiles other = (Profiles) obj;
		if (ProfileID != other.ProfileID)
			return false;
		return true;
	}
}
