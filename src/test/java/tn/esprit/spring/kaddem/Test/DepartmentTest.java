package tn.esprit.spring.kaddem.Test;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DepartmentTest {

    /*
    @InjectMocks
    IDepartementService iDepartementService=new IDepartementService() {
        @Override
        public List<Departement> retrieveAllDepartements() {
            return null;
        }

        @Override
        public Departement addDepartement(Departement d) {
            return null;
        }

        @Override
        public Departement updateDepartement(Departement d) {
            return null;
        }

        @Override
        public Departement retrieveDepartement(Integer idDepart) {
            return null;
        }

        @Override
        public void deleteDepartement(Integer idDepartement) {

        }
    };

     */
    @Mock
    IDepartementService iDepartementService;

    @Test
    public void testRetrievAllDepartement(){
        when(iDepartementService.retrieveAllDepartements()).thenReturn(Collections.emptyList());

        List<Departement> departementList = departementService.retrieveAllDepartements();
        Assertions.assertEquals(0, departementList.size());
    }

    @Test
    public void testDeleteDepartement() {
        // Mocking behavior to return optional containing a Departement object
        when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(departement));

        // Calling the deleteDepartement method
        departementService.deleteDepartement(2);

        // Verifying that the repository's delete method was called with the correct argument
        Mockito.verify(departementRepository).delete(departement);
    }

    @Mock
    DepartementRepository departementRepository= Mockito.mock(DepartementRepository.class);
    @InjectMocks
    DepartementServiceImpl departementService;

    Departement departement= new Departement(1,"math");
    List<Departement> listDepartment = new ArrayList<Departement>(){
        {
            add(new Departement(1,"physiques"));
            add(new Departement(2,"chimie"));
        }
    };

    @Test
    public void testRetrieveDepartement() {
        when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(departement));
        Departement departement1 = departementService.retrieveDepartement(2);
        Assertions.assertNotNull(departement1);
    }


    @Test
    public void testAddDepartement() {
        // Given
        Departement d = new Departement(4, "Test");

        // Mocking the behavior of departementRepository.save()
        when(departementRepository.save(d)).thenReturn(d);

        // When
        Departement addedDepartement = departementService.addDepartement(d);

        // Then
        Assertions.assertNotNull(addedDepartement);
        Assertions.assertEquals(d, addedDepartement);
        // You can add more assertions if needed
    }
    @Test
    public void testUpdateDepartement() {
        // Mocking behavior to return optional containing a Departement object
        when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(departement));

        // Creating a new Departement object with updated information
        Departement updatedDepartement = new Departement(1, "Updated Department");

        // Mocking behavior of the save method of departementRepository
        when(departementRepository.save(updatedDepartement)).thenReturn(updatedDepartement);

        // Calling the updateDepartement method with the updatedDepartement object
        Departement updated = departementService.updateDepartement(updatedDepartement);

        // Asserting that the updatedDepartement object is not null
        Assertions.assertNotNull(updated);

        // Asserting that the name of the updated department matches the expected value
        Assertions.assertEquals("Updated Department", updated.getNomDepart());
    }

}
