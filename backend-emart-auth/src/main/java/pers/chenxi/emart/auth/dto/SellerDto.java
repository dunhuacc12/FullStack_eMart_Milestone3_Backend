package pers.chenxi.emart.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * seller dto.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SellerDto extends UserDto {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String email;
	private String mobileNum;
	private String sex;
	private Integer age;
	private String adress;
	private String city;
	private String state;
	private String zip;
	private String companyName;
	private String gstin;
	private String cardNo;
	private String bank;
	private String status;
}
