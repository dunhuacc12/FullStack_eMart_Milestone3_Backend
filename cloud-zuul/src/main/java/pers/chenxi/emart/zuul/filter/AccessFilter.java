package pers.chenxi.emart.zuul.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Resource filter.<br>
 *
 * If the request header does not contain the authorization parameter value, the
 * request will be blocked
 *
 * @author XiChen
 *
 */
public class AccessFilter extends ZuulFilter {
	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Autowired
	private RestTemplate restTempate;

	/**
	 * Whether the filter will be executed.
	 *
	 * @return true :Verification required, false :No verification required
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		System.out.println(request.getRequestURI());
		if (request.getRequestURI().endsWith("/oauth/token")) {
			return false;
		} else if (request.getRequestURI().indexOf("/session/") > 0) {
			return false;
		} else if (request.getRequestURI().endsWith("/signup-buyer")) {
			return false;
		} else if (request.getRequestURI().endsWith("/signup-seller")) {
			return false;
		}

		return true;
	}

	/**
	 * filter Type.
	 *
	 * @return "pre": Execute before routing
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * filter Order
	 *
	 * @return The larger the sequence number, the lower the priority and the later
	 *         the execution
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * Execute filter.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		String url = request.getRequestURL().toString();
		if (url.endsWith("/oauth/token")) {
			return null;
		}

		Object accessToken = request.getHeader("Authorization");
		if (accessToken == null) {
			logger.warn("Authorization token is empty.");
			requestContext.setSendZuulResponse(false);
			requestContext.setResponseStatusCode(401);
			requestContext.setResponseBody("Authorization token is empty.");
			return null;
		} else {
			try {
				// If the User information can be returned, it proves that the token is valid
				Map<String, Object> user = restTempate.getForObject("http://localhost:8991/user",
						new HashMap<String, Object>().getClass());
				if (user == null || user.get("user") == null) {
					logger.info("The user is null...");
					requestContext.setSendZuulResponse(false);
					requestContext.setResponseStatusCode(401);
					requestContext.setResponseBody("Cannot get the user.");
				} else {
					logger.info("Login user: " + user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Authorization token is ok");
		return null;
	}
}
