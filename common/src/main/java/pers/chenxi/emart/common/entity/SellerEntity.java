package pers.chenxi.emart.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "e_seller")
@Getter
@Setter
public class SellerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(length = 32)
	private String userId;

	@Column(length = 32)
	private String password;

	@Column(length = 32)
	private String userName;

	private String email;

	@Column(length = 32)
	private String mobileNum;

	@Column(length = 32)
	private String sex;

	private Integer age;

	private String adress;

	private String city;

	private String state;

	private String zip;

	private String comName;

	@Column(length = 32)
	private String gstin;

	@Column(length = 32)
	private String cardNo;

	private String bank;

	@Column(length = 1)
	private String status;

	private Timestamp crtDate;

	@Column(length = 32)
	private String crtUserId;

	private Timestamp updDate;

	@Column(length = 32)
	private String updUserId;

	@Column(length = 1)
	private String delFlg;
}
