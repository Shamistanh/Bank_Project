package app.controller;


import app.entity.Customer;
import app.service.BankService;
import app.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("admin")
public class ManagerController {

    private final CustomerService customerService;
    private final BankService bankService;

    public ManagerController(CustomerService customerService, BankService bankService) {
        this.customerService = customerService;
        this.bankService = bankService;
    }

    @GetMapping("received")
    public List<Customer> allCustomers(){
        log.info("GET -> received");
        return bankService.receivedCustomers();

    }


    @PostMapping("decision")
    public Optional<Customer> send(@RequestParam("id") Integer cus_id, String decision){

        final Optional<Customer> customerById = customerService.getCustomerById(cus_id);
        if (customerById.isPresent()){
            customerById.get().setAcceptenceStatus(decision);
        }
        log.info("POST -> decision");

        return null;
    }
}
