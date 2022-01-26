package com.smart.entities;

import com.smart.entities.*;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private int id;
	
	@NotBlank(message = "Name should not blank")
	@Size(min = 3 ,max=12,message = "...")
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl,
			String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.about = about;
	}
	public User() {
		super();
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", about=" + about + ", list=" + list + "]";
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	private boolean enabled;
	private String imageUrl;
	
	@Column(length =500)
	private String about;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "user")
	private List<Contact> list = new ArrayList<Contact>();
	
	public int getId() {
		return id;
	}
	public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl,
			String about, List<Contact> list) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		this.about = about;
		this.list = list;
	}
	public List<Contact> getList() {
		return list;
	}
	public void setList(List<Contact> list) {
		this.list = list;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean getEnabled() {
		return enabled;
	}
		public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}

}
 