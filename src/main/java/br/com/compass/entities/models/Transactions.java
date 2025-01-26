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

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "transaction_type", nullable = false)
    private Integer transactionType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "account_cpf")
    private String accountCpf;

    @Column(name = "recipient_cpf")
    private String recipientCpf;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    public Transactions() {}

    public Transactions(Account account, Integer transactionType, Double amount, String accountCpf, String recipientCpf) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountCpf = accountCpf;
        this.recipientCpf = recipientCpf;
        this.transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public java.lang.String getAccountCpf() {
        return accountCpf;
    }

    public void setAccountCpf(java.lang.String senderCpf) {}

    public String getRecipientCpf() {
        return recipientCpf;
    }

    public void setRecipientCpf(String recipientCpf) {
        this.recipientCpf = recipientCpf;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
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
