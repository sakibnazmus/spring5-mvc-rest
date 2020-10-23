package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        saveCategories();
        log.debug("Data Loaded, Category count: " + categoryRepository.count());
        saveCusotmers();
        log.debug("Data Loaded, Customer count: " + categoryRepository.count());
        saveVendors();
        log.debug("Data Loaded, Vendor count: " + vendorRepository.count());
    }

    private void saveVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
    }

    private void saveCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);
    }

    private void saveCusotmers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Nazmus");
        customer1.setLastname("Sakib");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Mahjabin");
        customer2.setLastname("Mimu");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }
}
