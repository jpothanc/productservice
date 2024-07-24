package com.ib.it.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equity")
@EqualsAndHashCode
public class Equity {

    @Id
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "primary_exchange")
    private String primaryExchange;

    @Column(name = "lot_size")
    private Integer lotSize;

    @Column(name = "last_close_price")
    private BigDecimal lastClosePrice;

    @Column(name = "currency")
    private String currency;

    @Column(name = "day_20_average_volume")
    private Long day20AverageVolume;

    @Column(name = "day_30_average_volume")
    private Long day30AverageVolume;
}
