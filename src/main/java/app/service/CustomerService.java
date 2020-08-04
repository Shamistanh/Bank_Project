package app.service;


import app.entity.CreditInfo;
import app.entity.Customer;
import app.repo.CustomerRepo;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;


    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }

    private Double payAbleAmount(Double creditAmount, Integer creditDuaration) {
        return creditAmount / creditDuaration;
    }

    public List<Customer> validIncomedCustomers(Double creditAmount, Integer creditDuaration) {
        return findAllCustomers().stream()
                .filter(c -> c.getCusSalary() >= payAbleAmount(creditAmount, creditDuaration))
                .collect(Collectors.toList());
    }

    public Optional<Customer> getCustomerById(Integer id){
        return customerRepo.findById(id);
    }


    public List<Customer> reliabilityRanking() {

        Map<Customer, Integer> delays = new HashMap<>();
        for (Customer customer : findAllCustomers()) {
            int delay = 0;
            List<CreditInfo> creditInfo = customer.getCreditInfos();
            for (CreditInfo info : creditInfo) {
                if (info.getPayment_date().compareTo(info.getPaid_date()) <= 0) {
                    delay++;
                }
            }
            delays.put(customer, delay);
        }

        return delays.entrySet().stream()
                .sorted(comparingByValue()).map(Map.Entry::getKey).collect(Collectors.toList());

    }
}
