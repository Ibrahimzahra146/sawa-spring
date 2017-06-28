package com.secret.secret.model;

import java.sql.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
	@NotNull
	@Column(unique=true)

	String email;
	@JsonIgnore
	@NotNull
	String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	@NotNull
	Integer mobile;
	@NotNull
	String image;
	@NotNull
	Integer sign_in_out;
	@NotNull
	Integer anon_post;
	@NotNull
	//@Column(unique=true)
	Integer public_post_view;
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
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Integer getMobile() {
		return mobile;
	}
	public void setMobile(Integer mobile) {
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
	public Integer getAnon_post() {
		return anon_post;
	}
	public void setAnon_post(Integer anon_post) {
		this.anon_post = anon_post;
	}
	public Integer getPublic_post_view() {
		return public_post_view;
	}
	public void setPublic_post_view(Integer public_post_view) {
		this.public_post_view = public_post_view;
	}
	/* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    public Set<Friend> getfreinds() {
	        return friend;
	    }

	    public void setBooks(Set<Friend> friend) {
	        this.friend = friend;
	    }*/
	
	
	
	
}
