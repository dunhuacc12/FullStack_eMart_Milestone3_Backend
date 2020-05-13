package pers.chenxi.emart.common.util;

import java.sql.Timestamp;
import java.util.Date;

public class DateTimeUtils {

	private DateTimeUtils() {

	}

	/**
	 * get current time.
	 *
	 * @return current time
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}
}
