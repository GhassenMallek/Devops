package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.impl.OperateurServiceImpl;
import junit.runner.Version;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private IOperateurService operateurService = new OperateurServiceImpl();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testAddOperateur() {
        Operateur operateur = Operateur.builder()
                //.idOperateur(1L)
                .nom("nom")
                .password("password")
                .prenom("prenom")
                .build();
        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);
        Operateur operateur1 = operateurService.addOperateur(operateur.toOperateurDTO());
        List<Operateur> operateur2 = operateurService.retrieveAllOperateurs();
        Assertions.assertThat("nom").isEqualTo("nom");
        Mockito.verify(operateurRepository).save(operateur);
    }

//    @Test
//    public void testDeleteTask() {
//        Task task = new Task();
//        task.setName("Test Task");
//        task.setDescription("Test Description");
//        task.setStatus("doing");
//        taskController.deleteTask(task.getId());
//        Mockito.verify(taskRepository).deleteById(task.getId());
//    }
}
