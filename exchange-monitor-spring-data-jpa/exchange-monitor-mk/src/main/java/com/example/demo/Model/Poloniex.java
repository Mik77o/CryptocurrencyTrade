package com.example.demo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "poloniex")
public class Poloniex implements Serializable {

    private static final long serialVersionUID = -7865423308878250878L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "kurs")
    private Double exchangeRate;

    @Column(name = "data")
    private LocalDateTime date;

    @Column(name = "procent")
    private Double percent;

    protected Poloniex() {
    }

    public Poloniex(Double exchangeRate, LocalDateTime date, Double percent) {
        this.exchangeRate = exchangeRate;
        this.date = date;
        this.percent = percent;
    }

    @Override
    public String toString() {
        return String.format("Poloniex[id=%d, exchangeRate='%f', Date='%s', percentValue='%f']", id, exchangeRate, date, percent);
    }
}

