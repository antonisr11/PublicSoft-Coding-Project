package gr.publicstoft.springapp.repository;

import gr.publicstoft.springapp.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Optional<List<Supplier>> findByCompanyName(String companyName);
    Optional<Supplier> findByVat(String vat);
}