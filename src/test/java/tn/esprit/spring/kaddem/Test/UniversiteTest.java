package tn.esprit.spring.kaddem.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.IEquipeService;
import tn.esprit.spring.kaddem.services.IUniversiteService;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UniversiteTest {

    @InjectMocks
    IUniversiteService iUniversiteService= new IUniversiteService() {
        @Override
        public List<Universite> retrieveAllUniversites() {
            return null;
        }

        @Override
        public Universite addUniversite(Universite u) {
            return null;
        }

        @Override
        public Universite updateUniversite(Universite u) {
            return null;
        }

        @Override
        public Universite retrieveUniversite(Integer idUniversite) {
            return null;
        }

        @Override
        public void deleteUniversite(Integer idUniversite) {

        }

        @Override
        public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {

        }

        @Override
        public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
            return null;
        }
    };

    @Mock
    IUniversiteService iUniversiteServices = Mockito.mock(IUniversiteService.class);
    @Test
    @Order(3)
    public void testRetrieveAllUniversites(){
        when(iUniversiteServices.retrieveAllUniversites()).thenReturn(Collections.emptyList());

        List<Universite> universiteList = universiteService.retrieveAllUniversites();
        assertEquals(0, universiteList.size());
    }

    @Test
    public void testDeleteUniversite() {
        // Mocking behavior to return optional containing a Departement object
        when(universiteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(universite));

        // Calling the deleteDepartement method
        universiteService.deleteUniversite(2);

        // Verifying that the repository's delete method was called with the correct argument
        Mockito.verify(universiteRepository).delete(universite);
    }

    @Mock
    UniversiteRepository universiteRepository=  Mockito.mock(UniversiteRepository.class);
    @InjectMocks
    UniversiteServiceImpl universiteService;

    Universite universite = new Universite(1,"Esprit");

    List<Universite> listUniversite = new ArrayList<Universite>(){
        {
            add(new Universite(1,"Esprit"));
            add(new Universite(2,"UMT"));
        }
    };
//Universite
    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(universite));
        Universite universite1 = universiteService.retrieveUniversite(2);
        Assertions.assertNotNull(universite1);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUniversite() {
        // Given
        Universite u = new Universite(4, "Esprit");

        // Mocking the behavior of departementRepository.save()
        when(universiteRepository.save(u)).thenReturn(u);

        // When
        Universite addedUniversite = universiteService.addUniversite(u);

        // Then
        Assertions.assertNotNull(addedUniversite);
        assertEquals(u, addedUniversite);
        // You can add more assertions if needed
    }
    @Test
    public void testUpdateUniversite() {
        // Mocking behavior to return optional containing a Departement object
        when(universiteRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(universite));

        // Creating a new Departement object with updated information
        Universite updatedUniversite = new Universite(1, "Updated Universite");

        // Mocking behavior of the save method of departementRepository
        when(universiteRepository.save(updatedUniversite)).thenReturn(updatedUniversite);

        // Calling the updateDepartement method with the updatedDepartement object
        Universite updated = universiteService.updateUniversite(updatedUniversite);

        // Asserting that the updatedDepartement object is not null
        Assertions.assertNotNull(updated);

        // Asserting that the name of the updated department matches the expected value
        assertEquals("Updated Universite", updated.getNomUniv());
    }

    @Mock
    private DepartementRepository departementRepository;

    /*
    @Test
    public void testAssignUniversiteToDepartement() {
        // Mock data
        Universite universite = new Universite(1,"Esprit"); // Create a sample universite object
        Departement departement = new Departement(2,"Physique"); // Create a sample departement object

        when(departementRepository.save(departement)).thenReturn(departement);
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Return Optional.empty() instead of null when findById is called
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement));

        // Call the method to be tested
        iUniversiteServices.assignUniversiteToDepartement(1, 2);

        // Verify that the findById method of universiteRepository was called with the correct argument
        verify(universiteRepository).findById(1);

        // Verify that the findById method of departementRepository was called with the correct argument
        verify(departementRepository).findById(2);

        // Verify that the departement was added to the universite's departements set
        assertTrue(universite.getDepartements().contains(departement));

        // Verify that the save method of universiteRepository was called
        verify(universiteRepository).save(universite);
    }


     */

    @Test
    public void testRetrieveDepartementsByUniversite() {
        // Mock data
        Universite universite = new Universite(1,"Esprit"); // Create a sample universite object
        Set<Departement> departements = new HashSet<>(); // Create a sample set of departements
        Departement departement1 = new Departement(1,"maths"); // Sample departement object 1
        Departement departement2 = new Departement(2,"Physiques"); // Sample departement object 2
        departements.add(departement1);
        departements.add(departement2);
        universite.setDepartements(departements);

        // Stubbing repository method call
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        // Call the method to be tested
        Set<Departement> retrievedDepartements = universiteService.retrieveDepartementsByUniversite(1);

        // Verify that the findById method of universiteRepository was called with the correct argument
        verify(universiteRepository).findById(1);

        // Verify that the retrieved departements match the expected set
        assertEquals(departements, retrievedDepartements);
    }
}
