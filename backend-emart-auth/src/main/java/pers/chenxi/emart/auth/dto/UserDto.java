package pers.chenxi.emart.auth.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * base class for seller and buyer.
 */
@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;

	private String password;

	private String buySellFlg;

}
