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
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import tn.esprit.spring.kaddem.services.IEquipeService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EtudiantTest {
    @InjectMocks
    IEtudiantService iEtudiantService = new IEtudiantService() {
        @Override
        public List<Etudiant> retrieveAllEtudiants() {
            return null;
        }

        @Override
        public Etudiant addEtudiant(Etudiant e) {
            return null;
        }

        @Override
        public Etudiant updateEtudiant(Etudiant e) {
            return null;
        }

        @Override
        public Etudiant retrieveEtudiant(Integer idEtudiant) {
            return null;
        }

        @Override
        public void removeEtudiant(Integer idEtudiant) {

        }

        @Override
        public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {

        }

        @Override
        public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
            return null;
        }

        @Override
        public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
            return null;
        }
    };

    // Create a mock object for IEquipeService Etudiant
    @Mock
    IEtudiantService iEtudiantServices = Mockito.mock(IEtudiantService.class);
    @Test
    @Order(2)
    public void testRetrievAllEtudiant(){
        // Mocking the behavior of equipeRepository.findAll() method
        when(iEtudiantServices.retrieveAllEtudiants()).thenReturn(Collections.emptyList());



        List<Etudiant> EtudiantList = etudiantService.retrieveAllEtudiants();
        assertEquals(0, EtudiantList.size());
    }

    @Test
    public void testDeleteEtudiant() {
        // Mocking behavior to return optional containing a Departement object
        when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant));

        // Calling the deleteDepartement method
        etudiantService.removeEtudiant(2);

        // Verifying that the repository's delete method was called with the correct argument
        Mockito.verify(etudiantRepository).delete(etudiant);
    }

    @Mock
    EtudiantRepository etudiantRepository= Mockito.mock(EtudiantRepository.class);
    @InjectMocks
    EtudiantServiceImpl etudiantService;

    Etudiant etudiant= new Etudiant("cadal","evans");

    List<Etudiant> listOfEtudiant = new ArrayList<Etudiant>(){
        {
            add(new Etudiant("renne","irisse"));
            add(new Etudiant("lyon","maltois"));
        }
    };

    @Test
    public void testRetrieveEtudiant() {
        when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant));
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(2);
        Assertions.assertNotNull(etudiant1);
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEtudiant() {
        // Given
        Etudiant e = new Etudiant("Franck", "Louis");

        // Mocking the behavior of departementRepository.save()
        when(etudiantRepository.save(e)).thenReturn(e);

        // When
        Etudiant addedEtudiant = etudiantService.addEtudiant(e);

        // Then
        Assertions.assertNotNull(addedEtudiant);
        assertEquals(e, addedEtudiant);
        // You can add more assertions if needed
    }
    @Test
    public void testUpdateEtudiant() {
        // Mocking behavior to return optional containing a Departement object
        when(etudiantRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(etudiant));

        // Creating a new Departement object with updated information
        Etudiant updatedEtudiant = new Etudiant("nimes","senior" );

        // Mocking behavior of the save method of departementRepository
        when(etudiantRepository.save(updatedEtudiant)).thenReturn(updatedEtudiant);

        // Calling the updateDepartement method with the updatedDepartement object
        Etudiant updated = etudiantService.updateEtudiant(updatedEtudiant);

        // Asserting that the updatedDepartement object is not null
        Assertions.assertNotNull(updated);

        // Asserting that the name of the updated department matches the expected value
        assertEquals("nimes", updated.getNomE());
    }

    @Mock
    private DepartementRepository departementRepository;

    @Test
    public void testGetEtudiantsByDepartement() {
        // Mocking data
        Integer idDepartement = 1; // Sample departement ID
        Departement departement = new Departement(1,"physiques"); // Create a sample departement object
        departement.setIdDepart(idDepartement);

        Etudiant etudiant1 = new Etudiant("elm","ralph"); // Sample etudiant object 1
        Etudiant etudiant2 = new Etudiant("violette","elise"); // Sample etudiant object 2

        List<Etudiant> expectedEtudiants = new ArrayList<>();
        expectedEtudiants.add(etudiant1);
        expectedEtudiants.add(etudiant2);

        // Mocking the behavior of etudiantRepository.findEtudiantsByDepartement_IdDepart()
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(idDepartement)).thenReturn(expectedEtudiants);

        // Calling the method to be tested
        List<Etudiant> actualEtudiants = etudiantService.getEtudiantsByDepartement(idDepartement);

        // Verifying that the repository method was called with the correct argument
        // In this case, we don't need to explicitly verify the argument since we're using anyInt()
        // However, it's good practice to do so for clarity
        //verify(etudiantRepository).findEtudiantsByDepartement_IdDepart(idDepartement);

        // Verifying the size and contents of the returned list
        assertEquals(expectedEtudiants.size(), actualEtudiants.size());
        assertEquals(expectedEtudiants, actualEtudiants);
    }

}


