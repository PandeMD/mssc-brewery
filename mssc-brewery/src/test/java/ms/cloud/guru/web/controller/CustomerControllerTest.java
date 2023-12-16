package ms.cloud.guru.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import ms.cloud.guru.services.CustomerService;
import ms.cloud.guru.web.model.Customer;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@MockBean
	CustomerService customerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	Customer customer;
	
	@Before
    public Customer setUp() {

       return Customer.builder().id(UUID.randomUUID())
                .CustName("Ram")
                .build();
    }
	
	@Test
	void testGetCustomer() throws Exception {
		given(customerService.getCustomerById(any(UUID.class))).willReturn(setUp());
		
		mockMvc.perform( get("/api/v1/customer/" + setUp().getId().toString())
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(setUp().getId().toString())))
				.andExpect(jsonPath("$.CustName" , is("Ram")));
	}

	@Test
	void testHandlePost() throws Exception {
		Customer customer = setUp();
		customer.setId(null);
		
		Customer saveCust =	Customer.builder().id(UUID.randomUUID())
				 			.CustName("Sita").build();
		
		String custJson = objectMapper.writeValueAsString(customer);
		
		given(customerService.saveNewCustomer(any())).willReturn(saveCust);
		
		mockMvc.perform(post("/api/v1/customer/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(custJson))
				.andExpect(status().isCreated());
		
		then(customerService).should().saveNewCustomer(any());
	}

	@Test
	void testHandleUpdate() throws Exception {
		Customer customer = setUp();
		customer.setId(null);
		
		String custJson = objectMapper.writeValueAsString(customer);
		
		mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(custJson))
				.andExpect(status().isNoContent());
		
		then(customerService).should().updateCust(any(), any());
	}

	
}
