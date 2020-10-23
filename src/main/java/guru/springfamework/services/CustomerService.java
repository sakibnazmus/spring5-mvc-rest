package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getCustomers();
    public CustomerDTO getCustomerById(Long id);
    public CustomerDTO getCustomerByFirstName(String firstname);
    public CustomerDTO getCustomerByLastName(String lastname);
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
    public void deleteCustomerById(Long id);
}
