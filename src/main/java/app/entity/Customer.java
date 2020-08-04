package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    private Long id;

    @Column(name = "cus_name")
    private String cusName;

    @Column(name = "cus_FIN")
    private String cusFIN;

    @Column(name = "cus_AcceptenceStatus")
    private String acceptenceStatus;

    @Column(name = "cus_Salary")
    private Double cusSalary;


    @OneToMany(mappedBy = "customer")
    private List<CreditInfo> creditInfos;

}
