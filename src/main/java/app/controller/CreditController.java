package app.controller;


import app.entity.Customer;
import app.service.BankService;
import app.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class CreditController {

        private final CustomerService customerService;
        private final BankService bankService;

    public CreditController(CustomerService customerService, BankService bankService) {
        this.customerService = customerService;
        this.bankService = bankService;
    }

    @GetMapping("customers")
    public List<Customer> allCustomers(){
        log.info("GET -> allCustomers");
        return customerService.findAllCustomers();

    }

    @GetMapping("validIncomedCustomers")
    public List<Customer> validCustomers(@RequestParam("creditAmount") Double creditAmount, @RequestParam("creditDuaration") Integer creditDuaration){
        log.info("GET -> validCustomers");
        return customerService.validIncomedCustomers(creditAmount, creditDuaration);

    }

    @GetMapping("reliabilityRanking")
    public List<Customer> reliabilityRanking_Get(){
        log.info("GET -> reliabilityRanking");
        return customerService.reliabilityRanking();

    }

    @PostMapping("send")
    public List<Customer> send(@RequestParam("amount") Double amount,
                               @RequestParam("sendingMethod") String sendingMethod){
        List<Customer> availableCustomers = new ArrayList<>();

        bankService.send(amount,sendingMethod, availableCustomers);
        log.info("POST -> send");

        return null;
    }
}
