//package tn.esprit.spring.kaddem.Test;

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
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;
import tn.esprit.spring.kaddem.services.IEquipeService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
/*
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EquipeTest {

    @InjectMocks
    IEquipeService iEquipeService= new IEquipeService() {
        @Override
        public List<Equipe> retrieveAllEquipes() {
            return null;
        }

        @Override
        public Equipe addEquipe(Equipe e) {
            return null;
        }

        @Override
        public void deleteEquipe(Integer idEquipe) {

        }

        @Override
        public Equipe updateEquipe(Equipe e) {
            return null;
        }

        @Override
        public Equipe retrieveEquipe(Integer equipeId) {
            return null;
        }

        @Override
        public void evoluerEquipes() {

        }
    };

    // Create a mock object for IEquipeService
    @Mock
    IEquipeService iEquipeServices = Mockito.mock(IEquipeService.class);
    @Test
    @Order(2)
    public void testRetrievAllEquipe(){
        // Mocking the behavior of equipeRepository.findAll() method
        when(iEquipeServices.retrieveAllEquipes()).thenReturn(Collections.emptyList());



        List<Equipe> EquipeList = equipeService.retrieveAllEquipes();
        Assertions.assertEquals(0, EquipeList.size());
    }

    @Test
    public void testDeleteEquipe() {
        // Mocking behavior to return optional containing a Departement object
        when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));

        // Calling the deleteDepartement method
        equipeService.deleteEquipe(2);

        // Verifying that the repository's delete method was called with the correct argument
        Mockito.verify(equipeRepository).delete(equipe);
    }

    @Mock
    EquipeRepository equipeRepository= Mockito.mock(EquipeRepository.class);
    @InjectMocks
    EquipeServiceImpl equipeService;

    Equipe equipe= new Equipe("cadal", Niveau.JUNIOR);

    List<Equipe> listOfEquipes = new ArrayList<Equipe>(){
        {
            add(new Equipe("renne",Niveau.SENIOR));
            add(new Equipe("lyon",Niveau.SENIOR));
        }
    };

    @Test
    public void testRetrieveEquipe() {
        when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));
        Equipe equipe1 = equipeService.retrieveEquipe(2);
        Assertions.assertNotNull(equipe1);
    }


    @Test
    public void testAddEquipe() {
        // Given
        Equipe e = new Equipe("Franck", Niveau.SENIOR);

        // Mocking the behavior of departementRepository.save()
        when(equipeRepository.save(e)).thenReturn(e);

        // When
        Equipe addedEquipe = equipeService.addEquipe(e);

        // Then
        Assertions.assertNotNull(addedEquipe);
        Assertions.assertEquals(e, addedEquipe);
        // You can add more assertions if needed
    }
    @Test
    public void testUpdateEquipe() {
        // Mocking behavior to return optional containing a Departement object
        when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));

        // Creating a new Departement object with updated information
        Equipe updatedEquipe = new Equipe("nimes",Niveau.SENIOR );

        // Mocking behavior of the save method of departementRepository
        when(equipeRepository.save(updatedEquipe)).thenReturn(updatedEquipe);

        // Calling the updateDepartement method with the updatedDepartement object
        Equipe updated = equipeService.updateEquipe(updatedEquipe);

        // Asserting that the updatedDepartement object is not null
        Assertions.assertNotNull(updated);

        // Asserting that the name of the updated department matches the expected value
        Assertions.assertEquals("nimes", updated.getNomEquipe());
    }

    @Test
    public void testEvoluerEquipes() {
        // Stub data for equipeRepository.findAll()
       // when(equipeRepository.findAll()).thenReturn();
        when(equipeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(equipe));
        when(equipeRepository.save(any(Equipe.class))).thenReturn(null);
        equipeRepository.save(equipe);
        // Call the method to be tested
        equipeService.evoluerEquipes();
        equipeRepository.save(equipe);
        //Mockito.verify(equipeRepository).delete(equipe);

        // Verify the behavior
        // You can verify the save() method calls on equipeRepository based on the expected behavior of the method
        verify(equipeRepository, times(2)).save(any(Equipe.class)); // Adjust the expected number as needed
    }


}



 */
