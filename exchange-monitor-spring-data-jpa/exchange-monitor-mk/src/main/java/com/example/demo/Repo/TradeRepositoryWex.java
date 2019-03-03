package com.example.demo.Repo;

import com.example.demo.Model.Wex;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TradeRepositoryWex extends CrudRepository<Wex, Long> {
    @Query("SELECT MAX(exchangeRate) FROM Wex")
    Double getMaxValOfExchangeRate();

    @Query("SELECT MIN(exchangeRate) FROM Wex")
    Double getMinValOfExchangeRate();

    @Query("SELECT b.date FROM Wex b WHERE b.exchangeRate IN(SELECT MAX(d.exchangeRate) FROM Wex d)")
    List<LocalDateTime> getDate();

    @Query("SELECT b.date FROM Wex b WHERE b.exchangeRate IN(SELECT MIN(d.exchangeRate) FROM Wex d)")
    List<LocalDateTime> getDateMin();

    Wex findTopByOrderByIdDesc();
}
