package com.esprit.examen.services;
import com.esprit.examen.entities.*;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.services.impl.FournisseurServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {
    @Mock
    private FournisseurRepository fournisseurRepository;
    @Mock
    private SecteurActiviteRepository secteurActiviteRepository;
    @InjectMocks
    private IFournisseurService fournisseurService = new FournisseurServiceImpl();

    Fournisseur f1 = (Fournisseur.builder().
            idFournisseur(1L)
            .code("code1"))
            .libelle("libbele1")
            .categorieFournisseur((CategorieFournisseur) null)
            .factures((Set<Facture>) null)
            .secteurActivites(new HashSet<SecteurActivite>())
            .detailFournisseur((DetailFournisseur) null)
            .build();
    Fournisseur f2 = (Fournisseur.builder().
            idFournisseur(2L)
            .code("code2"))
            .libelle("libbele2")
            .categorieFournisseur((CategorieFournisseur) null)
            .factures((Set<Facture>) null)
            .secteurActivites(new HashSet<SecteurActivite>())
            .detailFournisseur((DetailFournisseur) null)
            .build();
    List<Fournisseur> s=new ArrayList<Fournisseur>(){{
        add(f1);
        add(f2) ;
        }
    };
    SecteurActivite sa = (SecteurActivite.builder().idSecteurActivite(5L).build());


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveFournisseurTest(){
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(f1));
        Fournisseur f =fournisseurService.retrieveFournisseur(1L);
        verify(fournisseurRepository).findById(Mockito.anyLong());
        assertNotNull(f);
        log.info("get fournisseur : "+f.toString());
    }
    @Test
    public void retrieveAllTest(){

        Mockito.when(fournisseurRepository.findAll()).thenReturn(s);
        List<Fournisseur> list = fournisseurService.retrieveAllFournisseurs();
        verify(fournisseurRepository).findAll();
        assertEquals(list,s);
        log.info("retrieve all fournissuers: "+ list.toString());
    }
    @Test
    public void addFournisseurTest(){
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(f1);

        Fournisseur f= fournisseurService.addFournisseur(f2.toFournisseurDTO());
        assertNotNull(f.getIdFournisseur());
        verify(fournisseurRepository).save((Mockito.any(Fournisseur.class)));
        log.info("get added fournisseur :"+ f.toString());
    }

    @Test
    public void updateFournisseurTest(){
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(f1);
        f1.setLibelle("libelle");
        Fournisseur f = fournisseurService.updateFournisseur(f2.toFournisseurDTO());
        assertNotNull(f);
        assertEquals("libelle",f.getLibelle());
        verify(fournisseurRepository).save(Mockito.any(Fournisseur.class));
        log.info("updated fournisseur :"+f.toString());
    }
    @Test
    public void deleteFournisseurTest(){
        fournisseurService.deleteFournisseur(f1.getIdFournisseur());
        verify(fournisseurRepository).deleteById(Mockito.anyLong());
        log.info("deleted fournisseur :"+f1.toString());
    }

    @Test
    public void assignSecteurActiviteToFournisseurTest(){
        Mockito.when((fournisseurRepository.findById(Mockito.anyLong()))).thenReturn(Optional.of(f1));
        Mockito.when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sa));
        fournisseurService.assignSecteurActiviteToFournisseur(5L,1L);
        assertTrue(f1.getSecteurActivites().contains(sa));
        verify(fournisseurRepository).save(Mockito.any(Fournisseur.class));
        log.info("secteur activite assignd :"+f1.toString());
    }


}


