package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.StockDTO;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.impl.StockServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;
	@InjectMocks
	private IStockService stockService = new StockServiceImpl();
	private Stock s1;
	private Stock s2;
	ModelMapper modelMapper;
	@BeforeEach
	public void init() {
		this.s1 = new Stock();
		this.s1.setIdStock(0L);
		this.s1.setLibelleStock("Test 1");
		this.s1.setQte(11);
		this.s1.setQteMin(11);

		this.s2 = new Stock();
		this.s2.setIdStock(1L);
		this.s2.setLibelleStock("Test 2");
		this.s2.setQte(22);
		this.s2.setQteMin(22);

		this.modelMapper = new ModelMapper();
	}
	@Test
	@DisplayName("Test Add to Stock")
	public void testAddStock() {
		init();
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		StockDTO sdto = modelMapper.map(s1, StockDTO.class);
		Stock snew = stockService.addStock(sdto);
		assertNotNull(snew);
		assertEquals(snew.getLibelleStock(),"Test 1");
	}

	@Test
	@DisplayName("Test Retrieve from stock")
	public void testRetrieveStock() {
		init();
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		StockDTO sdto = modelMapper.map(s1,StockDTO.class);
		Stock snew = stockService.addStock(sdto);
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		Stock existingStock = stockService.retrieveStock(snew.getIdStock());
		assertNotNull(existingStock);
	}
	@Test
	@DisplayName("Test Delete from stock")
	public void testDeleteStock() {
		init();
		Long StockId =1L;
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		doNothing().when(stockRepository).deleteById(anyLong());
		stockService.deleteStock(StockId);
		verify(stockRepository, times(1)).deleteById(StockId);
	}

	@Test
	@DisplayName("Test Update Stock")
	public void testUpdateStock() {
		init();
		when(stockRepository.findById(anyLong())).thenReturn(Optional.of(s1));
		when(stockRepository.save(any(Stock.class))).thenReturn(s1);
		s1.setLibelleStock("faza");
		StockDTO sdto=modelMapper.map(s1, StockDTO.class);
		Stock exisitingStock = stockService.updateStock(sdto);
		assertNotNull(exisitingStock);
		assertEquals("faza", exisitingStock.getLibelleStock());

	}
}
