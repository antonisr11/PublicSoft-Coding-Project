package gr.publicstoft.springapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import gr.publicstoft.springapp.model.entity.Supplier;
import gr.publicstoft.springapp.repository.SupplierRepository;
import gr.publicstoft.springapp.service.Impl.SupplierServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    public void testFindAll() {
        Supplier supplier1 = new Supplier("1", "CompanyA", "John", "Doe", "VAT123456", "Office1", "Address1", 12345, "City1", "Country1");
        Supplier supplier2 = new Supplier("2", "CompanyB", "Jane", "Doe", "VAT654321", "Office2", "Address2", 54321, "City2", "Country2");
        when(supplierRepository.findAll()).thenReturn(List.of(supplier1, supplier2));

        List<Supplier> suppliers = supplierService.findAll();
        assertNotNull(suppliers);
        assertEquals(2, suppliers.size());
    }

    @Test
    public void testFindByCompanyName() {
        Supplier supplier = new Supplier("1", "CompanyA", "John", "Doe", "VAT123456", "Office1", "Address1", 12345, "City1", "Country1");
        when(supplierRepository.findByCompanyName("CompanyA")).thenReturn(Optional.of(List.of(supplier)));

        List<Supplier> suppliers = supplierService.findByCompanyName("CompanyA");
        assertNotNull(suppliers);
        assertFalse(suppliers.isEmpty());
        assertEquals("CompanyA", suppliers.get(0).getCompanyName());
    }

    @Test
    public void testFindByVATFound() {
        Supplier supplier = new Supplier("1", "CompanyA", "John", "Doe", "VAT123456", "Office1", "Address1", 12345, "City1", "Country1");
        when(supplierRepository.findByVat("VAT123456")).thenReturn(Optional.of(supplier));

        Supplier supplierReturned = supplierService.findByVAT("VAT123456");
        assertNotNull(supplierReturned);
        assertEquals("VAT123456", supplierReturned.getVat());
    }

    @Test
    public void testFindByVATNotFound() {
        when(supplierRepository.findByVat("VAT999999")).thenReturn(Optional.empty());

        Supplier supplierReturned = supplierService.findByVAT("VAT999999");
        assertNull(supplierReturned);
    }

    @Test
    public void testSave() {
        Supplier supplier = new Supplier(null, "CompanyA", "John", "Doe", "VAT123456", "Office1", "Address1", 12345, "City1", "Country1");
        Supplier savedSupplier = new Supplier("1", "CompanyA", "John", "Doe", "VAT123456", "Office1", "Address1", 12345, "City1", "Country1");
        when(supplierRepository.save(supplier)).thenReturn(savedSupplier);

        Supplier supplierReturned = supplierService.save(supplier);
        assertNotNull(supplierReturned);
        assertEquals("1", supplierReturned.getId());
    }
}