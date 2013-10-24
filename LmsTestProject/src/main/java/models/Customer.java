package models;

import java.io.Serializable;
import java.util.Date;
 
public class Customer implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
		private int Id;
		private String Address; 
		private Date DateOfBirth;
		private Gender Gender;
		private String FirstName;
		private String LastName;
		private String Email;
		private String Phone;
		private String Password;
		
		public Customer(String address, Date dateOfBirth,
				models.Gender gender, String firstName, String lastName,
				String email, String phone, String password) {
			Address = address;
			DateOfBirth = dateOfBirth;
			Gender = gender;
			FirstName = firstName;
			LastName = lastName;
			Email = email;
			Phone = phone;
			Password = password;
		}
		public Customer()
		{}
		public Customer(int id)
		{this.Id = id;}
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getAddress() {
			return Address;
		}
		public void setAddress(String address) {
			Address = address;
		}
		public Date getDateOfBirth() {
			return DateOfBirth;
		}
		public void setDateOfBirth(Date dateOfBirth) {
			DateOfBirth = dateOfBirth;
		}
		public Gender getGender() {
			return Gender;
		}
		public void setGender(Gender gender) {
			Gender = gender;
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
		public String getPhone() {
			return Phone;
		}
		public void setPhone(String phone) {
			Phone = phone;
		}
		public String getPassword() {
			return Password;
		}
		public void setPassword(String password) {
			Password = password;
		}
		
}
