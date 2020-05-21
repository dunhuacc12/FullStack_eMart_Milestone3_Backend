package pers.chenxi.emart.common;

import java.util.List;

import lombok.Data;

/**
 * response base class.
 *
 * @author XiChen
 *
 * @param <T>
 *            response data.
 */
@Data
public class ResponseGeneric<T> {

	/** response data */
	private T data;

	/** message list */
	private List<String> message;

	/** response status */
	private Integer status;
}
