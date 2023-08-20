package com.mindtree.customer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mindtree.customer.entity.Customer;
import com.mindtree.customer.entity.repository.CustomerRepository;
import com.mindtree.customer.serviceImplementation.CustomerServiceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

	  @Mock
	  private CustomerRepository customerRepository;

	  @Mock
	  private Logger logger;

	  @InjectMocks
	  private CustomerServiceImplementation customerService;

	  @BeforeEach
	  public void setUp() {
	      MockitoAnnotations.initMocks(this);
	  }

	  @Test
	  public void testUpdateCustomer() {
	      int customerId = 1;
	      Customer existingCustomer = new Customer();
	      existingCustomer.setId(customerId);
	      existingCustomer.setUsername("oldUsername");
	      existingCustomer.setPassword("oldPassword");
	      existingCustomer.setMobileNo("oldMobile");

	      Customer updatedCustomer = new Customer();
	      updatedCustomer.setId(customerId);
	      updatedCustomer.setUsername("newUsername");
	      updatedCustomer.setPassword("newPassword");
	      updatedCustomer.setMobileNo("newMobile");

	      when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(existingCustomer));
	      when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

	      Customer result = customerService.updateCustomer(updatedCustomer, customerId);

	      verify(customerRepository, times(1)).findById(customerId);
	      verify(customerRepository, times(1)).save(existingCustomer);

	        // Assertions can be added based on your specific requirements
	  }

	  @Test
	  public void testDeleteCustomerById() {
	      int customerId = 1;
	      Customer existingCustomer = new Customer();
	      existingCustomer.setId(customerId);

	      when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(existingCustomer));

	      customerService.deleteCustomerById(customerId);

	      verify(customerRepository, times(1)).findById(customerId);
	      verify(customerRepository, times(1)).deleteById(customerId);
	    }

	  @Test
	  public void testSaveCustomer() throws JsonProcessingException {
	      Customer customer = new Customer();
	      customer.setUsername("username");
	      customer.setPassword("password");
	      customer.setMobileNo("1234567890");

	      customerService.saveCustomer(customer);

	      verify(logger, times(1)).info(anyString(), anyString());
	        // Assertions can be added based on your specific requirements
	  }
	  }


