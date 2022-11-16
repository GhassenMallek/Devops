package com.esprit.examen.services;

import com.esprit.examen.entities.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StockServiceImplJunitTest {
    @Autowired
    IStockService stockService;

    @Test
    public void addStock(){
        Stock stock = stockService.addStock(CreateStock().toStockDto());
        assertNotNull(stock);
        stockService.deleteStock(stock.getIdStock());
    }

    private Stock CreateStock() {
        Stock s = new Stock();
        s.setLibelleStock("TestLibelle");
        s.setQteMin(10);
        s.setQte(6);
        return s;
    }
}
