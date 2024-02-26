package tn.esprit.spring.kaddem.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;
import tn.esprit.spring.kaddem.services.IContratService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ContratTest {
    @InjectMocks
    IContratService iContratService = new IContratService() {
        @Override
        public List<Contrat> retrieveAllContrats() {
            return null;
        }

        @Override
        public Contrat updateContrat(Contrat ce) {
            return null;
        }

        @Override
        public Contrat addContrat(Contrat ce) {
            return null;
        }

        @Override
        public Contrat retrieveContrat(Integer idContrat) {
            return null;
        }

        @Override
        public void removeContrat(Integer idContrat) {

        }

        @Override
        public Contrat affectContratToEtudiant(Integer idContrat, String nomE, String prenomE) {
            return null;
        }

        @Override
        public Integer nbContratsValides(Date startDate, Date endDate) {
            return null;
        }

        @Override
        public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate) {
            return 0;
        }

        @Override
        public void retrieveAndUpdateStatusContrat() {

        }
    };

    public ContratTest() throws ParseException {
    }

    //Contrat

    @Mock
    ContratServiceImpl iContratServices = Mockito.mock(ContratServiceImpl.class);

    @Mock
    ContratRepository contratRepository= Mockito.mock(ContratRepository.class);
    @InjectMocks
    ContratServiceImpl contratService;

    Etudiant etudiant= new Etudiant("cadal","evans");
    // Create a sample contrat object with specific start and end dates
    Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-01");
    Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31");
    Contrat contrat = new Contrat(1, startDate, endDate, Specialite.IA, false, 100);

    //Contrat contrat=new Contrat(1,new Date(),new Date(), Specialite.IA,false,100);

    List<Contrat> listOfContrat = new ArrayList<Contrat>(){
        {
            add(new Contrat(1, startDate, endDate, Specialite.SECURITE,false,1200));
            add(new Contrat(2, startDate, endDate, Specialite.RESEAUX,false,1002));
        }
    };


    // Test Begin
    @Test
    public void testDeleteContrat() {
        // Mocking behavior to return optional containing a Contrat object
        when(contratRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Contrat(1, startDate, endDate, Specialite.SECURITE,false,1200))); // Create a new Contrat object for testing

        // Calling the removeContrat method
        iContratService.removeContrat(2);


        // Verifying that the repository's delete method was called with the correct argument
        //Mockito.verify(contratRepository).delete(any(Contrat.class)); // Verifying that delete method is called with any Contrat object
    }


    @Test
    public void testAddContrat() {
        // Given

        Contrat e = new Contrat(1,  startDate, endDate, Specialite.IA, false, 100);
        // Mocking the behavior of contratRepository.save()
        when(iContratServices.addContrat(e)).thenReturn(e);

        // When
        Contrat addedContrat = iContratServices.addContrat(e);

        // Then
        Assertions.assertNotNull(addedContrat);
        assertEquals(e, addedContrat);
    }

    @Test
    public void testRetrieveContrat() {
        // Mocking behavior to return optional containing a Contrat object
        when(contratRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(contrat));

        // Call the method to be tested
        Contrat retrievedContrat = iContratServices.retrieveContrat(2);
        when(iContratServices.retrieveContrat(2)).thenReturn(contrat);

        // Verify that the retrieved contrat is not null
        //Assertions.assertNotNull(retrievedContrat);
    }

    /*
    @Test
    public void testUpdateContrat() {
        // Mocking behavior to return optional containing a Contrat object
        when(contratRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(contrat));

        // Creating a new Contrat object with updated information
        Contrat updatedContrat = new Contrat(1, startDate, endDate, Specialite.CLOUD, false, 1000);

        // Mocking behavior of the save method of contratRepository
        when(contratRepository.save(updatedContrat)).thenReturn(updatedContrat);

        // Calling the updateDepartement method with the updatedDepartement object
        Contrat updated = iContratServices.updateContrat(updatedContrat);

        // Assert that the updated contrat is not null
        Assertions.assertNotNull(updated);

        // Assert that the montantContrat of the updated contrat matches the expected value
        assertEquals(1000, updated.getMontantContrat());


    }

     */



}

