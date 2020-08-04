package app.repo;

import app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {


}

