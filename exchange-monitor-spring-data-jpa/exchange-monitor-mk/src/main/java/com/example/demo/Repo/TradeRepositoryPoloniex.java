package com.example.demo.Repo;

import com.example.demo.Model.Poloniex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepositoryPoloniex extends CrudRepository<Poloniex, Long> {
    @Query("SELECT MAX(exchangeRate) FROM Poloniex")
    Double getMaxValOfExchangeRate();

    @Query("SELECT MIN(exchangeRate) FROM Poloniex")
    Double getMinValOfExchangeRate();

    @Query("SELECT b.date FROM Poloniex b WHERE b.exchangeRate IN(SELECT MAX(d.exchangeRate) FROM Poloniex d)")
    List<LocalDateTime> getDate();

    @Query("SELECT b.date FROM Poloniex b WHERE b.exchangeRate IN(SELECT MIN(d.exchangeRate) FROM Poloniex d)")
    List<LocalDateTime> getDateMin();

    Poloniex findTopByOrderByIdDesc();
}
