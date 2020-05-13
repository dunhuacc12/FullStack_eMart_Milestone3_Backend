package pers.chenxi.emart.auth.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * buyer dto.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BuyerDto extends UserDto {

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
	private String status;
}
