package com.example.demo.Repo;

import com.example.demo.Model.Bittrex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepositoryBittrex extends CrudRepository<Bittrex, Long> {
    @Query("SELECT MAX(exchangeRate) FROM Bittrex")
    Double getMaxValOfExchangeRate();

    @Query("SELECT MIN(exchangeRate) FROM Bittrex")
    Double getMinValOfExchangeRate();

    @Query("SELECT b.date FROM Bittrex b WHERE b.exchangeRate IN(SELECT MAX(d.exchangeRate) FROM Bittrex d)")
    List<LocalDateTime> getDate();

    @Query("SELECT b.date FROM Bittrex b WHERE b.exchangeRate IN(SELECT MIN(d.exchangeRate) FROM Bittrex d)")
    List<LocalDateTime> getDateMin();

    Bittrex findTopByOrderByIdDesc();
}
