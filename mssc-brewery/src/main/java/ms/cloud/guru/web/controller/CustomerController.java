package ms.cloud.guru.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ms.cloud.guru.services.CustomerService;
import ms.cloud.guru.web.model.Customer;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping("/{custId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable UUID custId) {
		return new ResponseEntity<Customer>(customerService.getCustomerById(custId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Customer> handlePost(@RequestBody Customer cust)
	{
		Customer saveCust = customerService.saveNewCustomer(cust);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/customer"+saveCust.getId().toString());
		
		return new ResponseEntity<Customer>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/{custId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void handleUpdate(@PathVariable UUID custId,@RequestBody Customer cust)
	{
		customerService.updateCust(custId,cust);
	}
	
	@DeleteMapping("/{custId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable UUID custId) {
		customerService.deleteById(custId);
	}
	

}
