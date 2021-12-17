package arbis.demo.app.domain;


import lombok.Data;
import net.sf.jasperreports.engine.JasperPrint;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "address")
    private String address;
    @Column(name = "number")
    private String number;


}
