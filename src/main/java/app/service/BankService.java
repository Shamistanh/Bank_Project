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
public class BankService {

    int sentCounter=0;

    private final CustomerRepo customerRepo;


    public BankService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public List<Customer> findAllCustomers() {
        return customerRepo.findAll();
    }


    public String send(Double amount, String sendingMethod, List<Customer> customers) {
        sentCounter++;

        if (sentCounter%3==0){
            return "error occured";
        }
        return "successful operation";
    }

    public List<Customer> receivedCustomers() {

        List<Customer> received = new ArrayList<>();
        return received;
    }
}
