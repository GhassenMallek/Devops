package com.esprit.examen.services;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.services.impl.OperateurServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {
    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Test
    public void testAddOperateur() {
        Operateur operateur = Operateur.builder()
                .nom("nom")
                .password("password")
                .prenom("prenom")
                .build();

        Mockito.when(operateurRepository.save(operateur)).thenReturn(operateur);
        Operateur operateur1 = operateurService.addOperateur(operateur.toOperateurDTO());
        org.assertj.core.api.Assertions.assertThat(operateur1.getNom()).isEqualTo("nom");
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
