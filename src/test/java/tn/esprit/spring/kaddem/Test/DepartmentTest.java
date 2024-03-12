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
import tn.esprit.spring.kaddem.controllers.DepartementRestController;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class DepartmentTest {

    //débuts autres methodes
   // private Departement departement;



    @Test
    public void testIdDepartement() {
        departement.setIdDepart(1);
        assertEquals(1, departement.getIdDepart());
    }

    @Test
    public void testNomDepart() {
        departement.setNomDepart("Informatique");
        assertEquals("Informatique", departement.getNomDepart());
    }

    //fin autres méthodes
    @Mock
    IDepartementService iDepartementService;

    @Test
    public void testRetrievAllDepartement(){
        when(iDepartementService.retrieveAllDepartements()).thenReturn(Collections.emptyList());

        List<Departement> departementList = departementService.retrieveAllDepartements();
        assertEquals(0, departementList.size());
    }

    @Test
    public void testDeleteDepartement() {
        // Mocking behavior to return optional containing a Departement object
        when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(departement));

        // Calling the deleteDepartement method
        departementService.deleteDepartement(2);

        // Verifying that the repository's delete method was called with the correct argument
        verify(departementRepository).delete(departement);
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
        assertNotNull(departement1);
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
        assertNotNull(addedDepartement);
        assertEquals(d, addedDepartement);
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
        assertNotNull(updated);

        // Asserting that the name of the updated department matches the expected value
        assertEquals("Updated Department", updated.getNomDepart());
    }

    @InjectMocks
    private DepartementRestController departementController;
    @Test
    public void testGetDepartements() {
        // Mock the service method
        List<Departement> mockDepartements = new ArrayList<>();
        mockDepartements.add(new Departement());
        when(iDepartementService.retrieveAllDepartements()).thenReturn(mockDepartements);

        // Call the controller method
        List<Departement> result = departementController.getDepartements();

        // Verify that the service method was called
        verify(iDepartementService).retrieveAllDepartements();

        // Assert the result
        assertEquals(mockDepartements, result);
    }

    @Test
    public void testRemoveDepartement() {
        // Call the controller method
        departementController.removeDepartement(1);

        // Verify that the service method was called with the correct argument
        verify(iDepartementService).deleteDepartement(1);
    }

    @Test
    public void testUpdateDepartementController() {
        // Create a sample Departement object
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Test Departement");

        // Mock the service method
        when(iDepartementService.updateDepartement(departement)).thenReturn(departement);

        // Call the controller method
        Departement result = departementController.updateDepartement(departement);

        // Verify that the service method was called with the correct argument
        verify(iDepartementService).updateDepartement(departement);

        // Assert the result
        assertEquals(departement, result);
    }

    @Test
    public void testRetrieveDepartementController() {
        // Create a sample Departement object
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Test Departement");

        // Mock the service method
        when(iDepartementService.retrieveDepartement(1)).thenReturn(departement);

        // Call the controller method
        Departement result = departementController.retrieveDepartement(1);

        // Verify that the service method was called with the correct argument
        verify(iDepartementService).retrieveDepartement(1);

        // Assert the result
        assertEquals(departement, result);
    }

    @Test
    public void testAddDepartementController() {
        // Create a sample Departement object
        Departement departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Test Departement");

        // Mock the service method
        when(iDepartementService.addDepartement(departement)).thenReturn(departement);

        // Call the controller method
        Departement result = departementController.addDepartement(departement);

        // Verify that the service method was called with the correct argument
        verify(iDepartementService).addDepartement(departement);

        // Assert the result
        assertEquals(departement, result);
    }

    //test set and get etudiant

    @Test
    public void testSetAndGetEtudiants() {
        // Create a sample set of etudiants
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(new Etudiant("John","Doe"));
        etudiants.add(new Etudiant("Alice","Doe"));

        // Set the etudiants using the setter method
        departement.setEtudiants(etudiants);

        // Get the etudiants using the getter method
        Set<Etudiant> retrievedEtudiants = departement.getEtudiants();

        // Check if the retrieved etudiants is not null
        assertNotNull(retrievedEtudiants);

        // Check if the retrieved etudiants is equal to the original set of etudiants
        assertEquals(etudiants, retrievedEtudiants);
    }

    @Test
    public void testConstructorWithNomDepart() {
        // Create a new Departement object with a specified nomDepart
        String nomDepart = "Computer Science";
        Departement departement = new Departement(nomDepart);

        // Verify that the nomDepart attribute is set correctly
        assertEquals(nomDepart, departement.getNomDepart());
    }
}
