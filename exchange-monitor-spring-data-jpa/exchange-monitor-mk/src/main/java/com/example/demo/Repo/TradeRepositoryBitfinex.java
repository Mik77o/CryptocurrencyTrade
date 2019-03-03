package com.example.demo.Repo;

import com.example.demo.Model.Bitfinex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepositoryBitfinex extends CrudRepository<Bitfinex, Long> {
    @Query("SELECT MAX(exchangeRate) FROM Bitfinex")
    Double getMaxValOfExchangeRate();

    @Query("SELECT MIN(exchangeRate) FROM Bitfinex")
    Double getMinValOfExchangeRate();

    @Query("SELECT b.date FROM Bitfinex b WHERE b.exchangeRate IN(SELECT MAX(d.exchangeRate) FROM Bitfinex d)")
    List<LocalDateTime> getDate();

    @Query("SELECT b.date FROM Bitfinex b WHERE b.exchangeRate IN(SELECT MIN(d.exchangeRate) FROM Bitfinex d)")
    List<LocalDateTime> getDateMin();

    Bitfinex findTopByOrderByIdDesc();
}
