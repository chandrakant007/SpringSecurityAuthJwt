package com.ck.practice.SpringSecurityAuthJwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//UserEntity.java
@Entity
@Table(name = "users")
@Data
public class UserEntity {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(unique = true, nullable = false)
 private String username;

 @Column(nullable = false)
 private String password; // encoded (BCrypt)

 @Column(nullable = false)
 private String role; // e.g. "ROLE_USER", "ROLE_ADMIN"

 public Long getId() {
	return id;
 }

 public void setId(Long id) {
	this.id = id;
 }

 public String getUsername() {
	return username;
 }

 public void setUsername(String username) {
	this.username = username;
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


 
}
