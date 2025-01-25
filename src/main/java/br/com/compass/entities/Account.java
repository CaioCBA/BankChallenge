package br.com.compass.entities;

import br.com.compass.entities.enums.AccountType;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_type", nullable = false)
    private Integer accountType;

    @Column(name = "total_balance")
    private Double totalBalance = 0.0;

    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Double getTotalBalance() { return totalBalance; }

    public void setTotalBalance(Double totalBalance) { this.totalBalance = totalBalance; }

    public AccountType getAccountType() {
        return AccountType.valueOf(accountType);
    }

    public void setAccountType(AccountType accountType) {
        if (accountType != null) {
            this.accountType = accountType.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}