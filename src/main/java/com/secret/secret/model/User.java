package com.secret.secret.model;

import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	Integer id; 
	
	@NotNull
	String first_name;
	@NotNull
	String last_name;
	@Column(unique=false)

	String email;
	String deviceId;
	@JsonIgnore
	@NotNull
	String password;
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String birthdate;
	@NotNull
	String mobile;
	@NotNull
	String image;
	@NotNull
	String cover_image;
	@NotNull
	Integer sign_in_out;
	
	@NotNull
	String  isPublic;
	@NotNull
	String  isProfileImagePublic;
	@NotNull
	String  themeColor;
	//@Column(unique=true)
	Integer public_post_view;
	@NotNull
	//@Column(unique=true)
	String gender;
	@NotNull
	//@Column(unique=true)
	boolean  isActive;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
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
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getSign_in_out() {
		return sign_in_out;
	}
	public void setSign_in_out(Integer sign_in_out) {
		this.sign_in_out = sign_in_out;
	}

	public Integer getPublic_post_view() {
		return public_post_view;
	}
	public void setPublic_post_view(Integer public_post_view) {
		this.public_post_view = public_post_view;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCover_image() {
		return cover_image;
	}
	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
	}
	
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	public String getIsProfileImagePublic() {
		return isProfileImagePublic;
	}
	public void setIsProfileImagePublic(String isProfileImagePublic) {
		this.isProfileImagePublic = isProfileImagePublic;
	}
	public boolean getIsctive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	/* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    public Set<Friend> getfreinds() {
	        return friend;
	    }

	    public void setBooks(Set<Friend> friend) {
	        this.friend = friend;
	    }*/
	
	
	
	
}
