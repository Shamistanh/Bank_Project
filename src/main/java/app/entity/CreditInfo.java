package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Entity
public class CreditInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_id")
    private Long id;

    @Column(name = "payment_date")
    private Date payment_date;

    @Column(name = "cus_id")
    private Date cus_id;

    @Column(name = "paid_date")
    private Date paid_date;

    @ManyToOne
    @JoinTable(name = "cust_credit",
            joinColumns = { @JoinColumn(
                    name = "cr_id",
                    referencedColumnName = "cr_id"
            ) },
            inverseJoinColumns = { @JoinColumn(
                    name = "cus_id",
                    referencedColumnName = "cus_id"
            ) }
    )
    private Customer customer;
}
