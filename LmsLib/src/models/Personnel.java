package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="Personnel")
public class Personnel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int Id;
	private String FirstName;
	private String LastName;
	private String Email;
	@Temporal(TemporalType.DATE)
	private Date DateOfBirth;
	private String Address;
	private String Phone;
	private String Title;
	private Gender Gender;
	public Personnel(){}
	public Personnel(int id)
	{
		this.Id = id;
	}
	public Personnel(int id, String firstName, String lastName, String email,
			Date dateOfBirth, String address, String phone, String title,
			models.Gender gender) {
		super();
		Id = id;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		DateOfBirth = dateOfBirth;
		Address = address;
		Phone = phone;
		Title = title;
		Gender = gender;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Gender getGender() {
		return Gender;
	}
	public void setGender(Gender gender) {
		Gender = gender;
	}
}
