package gr.publicstoft.springapp.controller;

import gr.publicstoft.springapp.model.entity.Supplier;
import gr.publicstoft.springapp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suppliers")
public class SupplierController {
    private final SupplierService service;

    @Autowired
    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Supplier>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/find/companyName/{companyName}")
    public ResponseEntity<List<Supplier>> findAllByCompanyName(@PathVariable("companyName") String companyName) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCompanyName(companyName));
    }

    @GetMapping("/find/vat/{vat}")
    public ResponseEntity<Supplier> findAllByVAT(@PathVariable("vat") String vat) {
        Supplier supplier = service.findByVAT(vat);
        if (supplier == null) {
            throw new RuntimeException("Supplier with vat " + vat + " not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(supplier);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Supplier> save(@RequestBody Supplier supplier) {
        Supplier supplierSaved = service.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierSaved);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
