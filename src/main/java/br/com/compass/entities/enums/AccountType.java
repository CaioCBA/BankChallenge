package br.com.compass.entities.enums;

public enum AccountType {
    PAYMENTS_ACCOUNT(1),
    SAVINGS_ACCOUNT(2),
    CHECKING_ACCOUNT(3);

    private final int code;

    AccountType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AccountType valueOf(int code) {
        for(AccountType value : AccountType.values() ) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("No account type with code " + code);
    }
}
