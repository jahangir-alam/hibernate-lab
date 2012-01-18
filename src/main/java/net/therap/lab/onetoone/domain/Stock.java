package net.therap.lab.onetoone.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jahangir
 * @since 1/12/12 8:07 PM
 */
@Entity
@Table(name = "STOCK", uniqueConstraints = {
        @UniqueConstraint(columnNames = "STOCK_NAME"),
        @UniqueConstraint(columnNames = "STOCK_CODE")})
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
    private String stockCode;

    @Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
    private String stockName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "stock")
    private StockDetail stockDetail;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY_ID")
    private AppUser createdBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public StockDetail getStockDetail() {
        return stockDetail;
    }

    public void setStockDetail(StockDetail stockDetail) {
        this.stockDetail = stockDetail;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return stockCode + " - " + stockName;
    }
}
