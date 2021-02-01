package com.ozan.exchange.api.model.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Conversion {

    @Id
    @GeneratedValue
    public UUID transactionId;

    public String source;

    public String target;

    public Double amount;

    public Double conversionAmount;

    public LocalDate date;
}
