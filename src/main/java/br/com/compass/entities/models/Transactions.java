package br.com.compass.entities.models;

import br.com.compass.entities.enums.TransactionType;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transactions implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "transaction_type", nullable = false)
    private Integer transactionType;

    @Column(name="amount", nullable = false)
    private Double amount;

    @Column(name = "account_cpf")
    private String accountCpf;

    @Column(name = "recipient_cpf")
    private String recipientCpf;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime date;

    public Transactions() {
    }

    public Transactions(Account account, Integer transactionType, Double amount, String accountCpf, String recipientCpf) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountCpf = accountCpf;
        this.recipientCpf = recipientCpf;
        this.date = LocalDateTime.now();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account acc) {
        this.account = acc;
    }

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return TransactionType.valueOf(transactionType);
    }

    public void setTransactionType(TransactionType transactionType) {
        if (transactionType != null) {
            this.transactionType = transactionType.getCode();
        }
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRecipientCpf() {
        return recipientCpf;
    }

    public String getAccountCpf() {
        return accountCpf;
    }

    public void setAccountCpf(String accountCpf) {
        this.accountCpf = accountCpf;
    }

    public void setRecipientCpf(String recipientCpf) {
        this.recipientCpf = recipientCpf;
    }

    public void setOwnerCpf(String recipientCpf) {
        this.recipientCpf = recipientCpf;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}