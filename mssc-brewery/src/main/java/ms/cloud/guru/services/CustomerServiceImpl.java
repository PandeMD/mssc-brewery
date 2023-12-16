package ms.cloud.guru.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ms.cloud.guru.web.model.Customer;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer getCustomerById(UUID custId) {
		return Customer.builder().id(UUID.randomUUID())
				.CustName("Ram")
				.build();
	}

	@Override
	public Customer saveNewCustomer(Customer cust) {
		return Customer.builder()
				.id(UUID.randomUUID())
				.build();
	}

	@Override
	public void updateCust(UUID custId, Customer cust) {
		
	}
	
	@Override
	public void deleteById(UUID custId) {
		log.debug("Deleting A Customer....");
	}

	

}
