package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.entities.dto.StockDTO;
import com.esprit.examen.repositories.StockRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;
	@InjectMocks
	private IStockService stockService = new StockServiceImpl();

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testAddStock() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();
		Stock stock = Stock.builder()
				.libelleStock("stock test")
				.qte(10)
				.qteMin(100)
				.build();
		Mockito.when(stockRepository.save(any(Stock.class))).thenReturn(stock);
		Stock savedStock= stockService.addStock(stock.toStockDto());
		Assertions.assertThat(savedStock.getLibelleStock()).isEqualTo("stock test");
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		Mockito.verify(stockRepository).save(any(Stock.class));
		//stockService.deleteStock(savedStock.getIdStock());
		
	}

	@Test
	public void testUpdateStock() {

	}

	/*
	@Test
	public void testAddStockOptimized() {

		Stock savedStock= stockService.addStock(StockDTO.builder()
				.libelleStock("stock test")
				.qte(10)
				.qteMin(100)
				.build());
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		stockService.deleteStock(savedStock.getIdStock());
		
	} 
	
	@Test
	public void testDeleteStock() {
		Stock savedStock= stockService.addStock(StockDTO.builder()
				.libelleStock("stock test")
				.qte(30)
				.qteMin(60)
				.build());
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}

	@Test
	public void testUpdateStock() {
		Stock savedStock= stockService.addStock(StockDTO.builder()
				.libelleStock("stock test")
				.qte(30)
				.qteMin(60)
				.build());
		stockService.updateStock(StockDTO.builder()
				.libelleStock("stock update test")
				.qte(300)
				.qteMin(600)
				.build());
	}
*/
}
