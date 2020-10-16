package com.neo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShiroApplicationTests {

	private static final String APPLICATION_JSON_UTF8 = "application/json;charset=UTF-8";
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

}
