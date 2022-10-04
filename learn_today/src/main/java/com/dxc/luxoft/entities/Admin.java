package com.dxc.luxoft.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="admins")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "admin_id")
	public int adminId;
	
	@Column(name = "admin_name")
	public String adminName;
	
	@Column(name = "admin_email", unique = true)
	public String adminEmail;
	
	@Column(name = "qualification")
	public String qualification;
	
	@Column(name = "register_date")
	public String registerdate;

}
