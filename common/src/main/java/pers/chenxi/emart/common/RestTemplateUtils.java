package pers.chenxi.emart.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestTemplateUtils {

	/**
	 * get JSON object for send request.
	 *
	 * @param json
	 *            JSON string
	 * @return request
	 */
	public static HttpEntity<String> getJSONObj4Request(String json) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(json, headers);
		return formEntity;
	}

}
