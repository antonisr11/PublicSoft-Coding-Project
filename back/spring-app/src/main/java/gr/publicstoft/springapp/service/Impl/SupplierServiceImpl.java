package gr.publicstoft.springapp.service.Impl;

import gr.publicstoft.springapp.model.entity.Supplier;
import gr.publicstoft.springapp.repository.SupplierRepository;
import gr.publicstoft.springapp.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);

    @Autowired
    public SupplierServiceImpl(SupplierRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Supplier> findAll() {
        logger.debug("{} - findAll called", getClass().getSimpleName());
        return repository.findAll();
    }

    @Override
    public List<Supplier> findByCompanyName(String companyName) {
        logger.debug("{} - findByCompanyName called (companyName = {})", getClass().getSimpleName(), companyName);
        return repository.findByCompanyName(companyName).orElse(Collections.emptyList());
    }

    @Override
    public Supplier findByVAT(String vat) {
        logger.debug("{} - findByVAT called (vat = {})", getClass().getSimpleName(), vat);
        return repository.findByVat(vat).orElse(null);
    }

    @Override
    @Transactional
    public Supplier save(Supplier supplier) {
        logger.debug("{} - save called with entity: {}", getClass().getSimpleName(), supplier.toString());
        return repository.save(supplier);
    }

    @Override
    @Transactional
    public void delete(String supplierId) {
        logger.debug("{} - delete called with id: {}", getClass().getSimpleName(), supplierId);
        repository.deleteById(supplierId);
    }
}
