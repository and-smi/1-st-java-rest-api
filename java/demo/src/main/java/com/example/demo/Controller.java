package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final CommonInterface commonInterface;

    @Autowired
    public Controller(CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    @PostMapping(value = "/createstaff")
    public ResponseEntity<?> createStaff(@RequestBody InfoStaff staff) {
        boolean createdStaff = commonInterface.createStaff(staff);
        return createdStaff
            ? new ResponseEntity<>(HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/createfirm")
    public ResponseEntity<?> createFirm(@RequestBody InfoOrganisation firm) {
        boolean createdFirm = commonInterface.createFirm(firm);
        return createdFirm
            ? new ResponseEntity<>(HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping(value = "/readstaff")
    public ResponseEntity<List<InfoStaff>> readStaff() {
        final List<InfoStaff> staff = commonInterface.readAll();
        return staff != null && !staff.isEmpty()
            ? new ResponseEntity<>(staff, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/readfirm")
    public ResponseEntity<List<InfoOrganisation>> readFirm() {
        final List<InfoOrganisation> firm = commonInterface.readAllFirms();
        return firm != null && !firm.isEmpty()
            ? new ResponseEntity<>(firm, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/readstaff/{id}")
    public ResponseEntity<InfoStaff> readStaff(@PathVariable(name = "id") int id) {
        final InfoStaff getStaffById = commonInterface.readStaff(id);
        return getStaffById.getStaffId() != 0
            ? new ResponseEntity<>(getStaffById, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/readfirm/{id}")
    public ResponseEntity<InfoOrganisation> readFirm(@PathVariable(name = "id") int id) {
        final InfoOrganisation firmId = commonInterface.readFirm(id);
        return firmId != null
            ? new ResponseEntity<>(firmId, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/updatestaff/{id}")
    public ResponseEntity<?> updateStaff(@PathVariable(name = "id") int id, @RequestBody InfoStaff staff){
        final boolean updatedStaff = commonInterface.updateStaff(staff, id);
        return updatedStaff
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/updatefirm/{id}")
    public ResponseEntity<?> updateFirm(@PathVariable(name = "id") int id, @RequestBody InfoOrganisation firm){
        final boolean updatedFirm = commonInterface.updateFirm(firm, id);
        return updatedFirm
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/deletestaff/{id}") 
    public ResponseEntity<?> deleteStaff(@PathVariable(name = "id") int id) {
            boolean deletedStaff = commonInterface.deleteStaff(id);
            return deletedStaff
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/deletefirm/{id}") 
    public ResponseEntity<?> deleteFirm(@PathVariable(name = "id") int id) {
            boolean deletedFirm = commonInterface.deleteFirm(id);
            return deletedFirm 
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}