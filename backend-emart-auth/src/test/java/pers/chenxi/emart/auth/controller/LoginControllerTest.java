package pers.chenxi.emart.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pers.chenxi.emart.auth.BackendEmartAuthApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendEmartAuthApplication.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	void loginTest() throws Exception {
		String content = "{\"userId\":\"100001\", \"password\":\"123456\"}";
		MvcResult mvcResult = mockMvc
				.perform(post("/session/100001").contentType(MediaType.APPLICATION_JSON).content(content))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		mvcResult.getResponse().setCharacterEncoding("UTF-8");
		int status = mvcResult.getResponse().getStatus();
		String responseString = mvcResult.getResponse().getContentAsString();
		System.out.println(responseString);
		Assert.assertEquals(200, status);
	}
}
