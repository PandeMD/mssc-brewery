package ms.cloud.guru.services;

import java.util.UUID;

import ms.cloud.guru.web.model.Customer;

public interface CustomerService {

	Customer getCustomerById(UUID custId);

	Customer saveNewCustomer(Customer cust);

	void updateCust(UUID custId, Customer cust);

	void deleteById(UUID custId);

}
