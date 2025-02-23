package gr.publicstoft.springapp.service;

import gr.publicstoft.springapp.model.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> findAll();

    List<Supplier> findByCompanyName(String companyName);

    Supplier findByVAT(String vat);

    Supplier save(Supplier supplier);

    void delete(String supplierId);
}
