package com.revolut.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "TRANSFER_TRANSACTION")
public class TransferTransaction {
    @Id
    @Column(name = "ID", nullable = false)
    @NotNull
    @JsonProperty
    private Long id;
//    private Account from;
//    private Account to;

    @Columns(columns = { @Column(name = "CURRENCY"), @Column(name = "AMOUNT") })
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency")
    private Money amount;
}
