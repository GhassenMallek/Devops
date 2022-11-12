package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.impl.OperateurServiceImpl;
import junit.runner.Version;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

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
        Mockito.when(operateurRepository.save(any(Operateur.class))).thenReturn(operateur);
        Operateur operateur1 = operateurService.addOperateur(operateur.toOperateurDTO());

        Assertions.assertThat(operateur1.getNom()).isEqualTo("nom");
        Mockito.verify(operateurRepository).save(any(Operateur.class));
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
