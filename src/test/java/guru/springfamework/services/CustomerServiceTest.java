package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "John";
    public static final String LASTNAME = "Doe";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void testGetCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOs = customerService.getCustomers();
        assertEquals(3, customerDTOs.size());
    }

    @Test
    void testGetCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO returnedCustomer = customerService.getCustomerById(ID);

        assertEquals(ID, returnedCustomer.getId());
    }

    @Test
    void testGetCustomerByFirstName() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findByFirstname(anyString())).thenReturn(customer);
        CustomerDTO returnedCustomer = customerService.getCustomerByFirstName(FIRSTNAME);

        assertEquals(FIRSTNAME, returnedCustomer.getFirstname());
    }

    @Test
    void testGetCustomerByLastName() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.findByLastname(anyString())).thenReturn(customer);
        CustomerDTO returnedCustomer = customerService.getCustomerByLastName(LASTNAME);

        assertEquals(LASTNAME, returnedCustomer.getLastname());
    }

    @Test
    void testCreateNewCustomer() {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.save(any())).thenReturn(CustomerMapper.INSTANCE.customerDTOToCustomer(customer));
        CustomerDTO savedCustomer = customerService.createNewCustomer(customer);

        assertEquals(ID, savedCustomer.getId());
        assertEquals(FIRSTNAME, savedCustomer.getFirstname());
        assertEquals(LASTNAME, savedCustomer.getLastname());
    }

    @Test
    void testSaveCustomerByDTO() {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        when(customerRepository.save(any())).thenReturn(CustomerMapper.INSTANCE.customerDTOToCustomer(customer));
        CustomerDTO savedCustomer = customerService.saveCustomerByDTO(ID, customer);

        assertEquals(ID, savedCustomer.getId());
        assertEquals(FIRSTNAME, savedCustomer.getFirstname());
        assertEquals(LASTNAME, savedCustomer.getLastname());
    }

    @Test
    void testDeleteCustomerById() {
        customerService.deleteCustomerById(ID);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}