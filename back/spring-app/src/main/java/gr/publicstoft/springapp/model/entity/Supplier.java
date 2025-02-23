package gr.publicstoft.springapp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @ColumnDefault("(uuid())")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "company_name", nullable = false, length = 225)
    private String companyName;

    @Column(name = "first_name", nullable = false, length = 225)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 225)
    private String lastName;

    @Column(name = "vat", nullable = false, length = 9, unique = true)
    private String vat;

    @Column(name = "irs_office", length = 225)
    private String irsOffice;

    @Column(name = "address", length = 225)
    private String address;

    @Column(name = "zip_code")
    private Integer zipCode;

    @Column(name = "city", length = 225)
    private String city;

    @Column(name = "country", length = 225)
    private String country;
}