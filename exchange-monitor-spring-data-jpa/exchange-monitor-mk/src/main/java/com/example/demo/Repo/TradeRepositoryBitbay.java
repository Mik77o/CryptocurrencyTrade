package com.example.demo.Repo;

import com.example.demo.Model.Bitbay;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepositoryBitbay extends CrudRepository<Bitbay, Long> {
    @Query("SELECT MAX(exchangeRate) FROM Bitbay")
    Double getMaxValOfExchangeRate();

    @Query("SELECT MIN(exchangeRate) FROM Bitbay")
    Double getMinValOfExchangeRate();

    @Query("SELECT b.date FROM Bitbay b WHERE b.exchangeRate IN(SELECT MAX(d.exchangeRate) FROM Bitbay d)")
    List<LocalDateTime> getDate();

    @Query("SELECT b.date FROM Bitbay b WHERE b.exchangeRate IN(SELECT MIN(d.exchangeRate) FROM Bitbay d)")
    List<LocalDateTime> getDateMin();

    Bitbay findTopByOrderByIdDesc();
}
