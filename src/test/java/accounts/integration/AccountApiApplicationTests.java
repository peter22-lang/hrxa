package accounts.integration;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;
import accounts.service.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountApiApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	
	  @Test 
	  public void testPostAccount() throws Exception {
		  Account account = new	  Account(1L, "Chris", "James", AccountType.CURRENT);
		  ObjectMapper objectMapper  = new ObjectMapper();	  
		  MockHttpServletResponse response = mockMvc.perform( post("/accounts")
				  .contentType("application/json")
				  .header("x-api-key", "Account_Auth")
				  .content(objectMapper.writeValueAsString(account))) .andDo(print()) 
				  .andExpect(jsonPath("id",  greaterThan(0)))
				  .andExpect(jsonPath("firstName").value("Chris"))
				  .andExpect(jsonPath("lastName").value("James"))
				  .andExpect(status().isCreated()) .andReturn().getResponse();
	  
		  Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
		  assertNotNull(accountService.getAccountById(id));
	  
	  }
	 

	@Test
	public void testGetAccount() throws Exception {
			Account account = new Account(1L,"Jack","Taylor", AccountType.SAVINGS);
			ObjectMapper objectMapper = new ObjectMapper();
			MockHttpServletResponse response = mockMvc.perform(post("/accounts")
					.contentType("application/json")
					.header("x-api-key", "Account_Auth")
					.content(objectMapper.writeValueAsString(account)))
					.andDo(print())
					.andExpect(jsonPath("$.id", greaterThan(0)))
					.andExpect(jsonPath("$.firstName").value("Jack"))
					.andExpect(jsonPath("$.lastName").value("Taylor"))
					.andExpect(status().isCreated()).andReturn().getResponse();
			Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
			mockMvc.perform(get("/accounts/{id}",id)
					.header("x-api-key", "Account_Auth"))			
			.andDo(print())
			.andExpect(jsonPath("id", greaterThan(0)))
			.andExpect(jsonPath("firstName").value("Jack"))
			.andExpect(jsonPath("lastName").value("Taylor"))
			.andExpect(status().isOk());
	}

}
