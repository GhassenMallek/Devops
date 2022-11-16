package com.esprit.examen.services;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.services.impl.SecteurActiviteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class SecteurActiviteServiceImplTest {
    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;
    @InjectMocks
    private ISecteurActiviteService secteurActiviteService = new SecteurActiviteServiceImpl();

    SecteurActivite s1 = (SecteurActivite.builder()
            .idSecteurActivite(1L)
            .codeSecteurActivite("code1")
            .libelleSecteurActivite("libelle1")
            .fournisseurs(new HashSet<Fournisseur>())
            .build());
    SecteurActivite s2 = (SecteurActivite.builder()
            .idSecteurActivite(2L)
            .codeSecteurActivite("code2")
            .libelleSecteurActivite("libelle2")
            .fournisseurs(new HashSet<Fournisseur>())
            .build());
    List<SecteurActivite> l = new ArrayList<SecteurActivite>(){{
        add(s1);
        add(s2);
    }};
    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void retrieveAllSecteurActiviteTest() {
        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(l);
        List<SecteurActivite> list = secteurActiviteService.retrieveAllSecteurActivite();
        verify(secteurActiviteRepository).findAll();
        assertEquals(list, l);
        log.info("retrieve all secteurActivite: "+ list.toString());
    }
    @Test
    public void addSecteurActiviteTest(){
        Mockito.when(secteurActiviteRepository.save(Mockito.any(SecteurActivite.class))).thenReturn(s1);
        SecteurActivite s = secteurActiviteService.addSecteurActivite(s2.toSecteurActiviteDTO());
        assertNotNull(s.getIdSecteurActivite());
        verify(secteurActiviteRepository).save(Mockito.any(SecteurActivite.class));
        log.info("get added secteurActivite :"+ s.toString());
    }
    @Test
    public void deleteSecteurActiviteTest(){
        secteurActiviteService.deleteSecteurActivite(s1.getIdSecteurActivite());
        verify(secteurActiviteRepository).deleteById(Mockito.anyLong());
        log.info("deleted secteurActivite :" + s1.toString());
    }
    @Test
    public void updateSecteurActiviteTest(){
        Mockito.when(secteurActiviteRepository.save(Mockito.any(SecteurActivite.class))).thenReturn(s1);
        s1.setLibelleSecteurActivite("libelle");
        SecteurActivite s = secteurActiviteService.updateSecteurActivite(s2.toSecteurActiviteDTO());
        assertNotNull(s);
        assertEquals("libelle", s.getLibelleSecteurActivite());
        verify(secteurActiviteRepository).save(Mockito.any(SecteurActivite.class));
        log.info("updated secteurActivite :"+s.toString());
    }
    @Test
    public void retrieveSecteurActiviteTest(){
        Mockito.when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(s1));
        SecteurActivite s = secteurActiviteService.retrieveSecteurActivite(1L);
        verify(secteurActiviteRepository).findById(Mockito.anyLong());
        assertNotNull(s);
        log.info("get secteurActivite : "+s.toString());
    }
}
