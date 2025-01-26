package br.com.compass.entities.enums;

public enum TransactionType {
    DEPOSIT(1),
    WITHDRAW(2),
    TRANSFER_SENT(3),
    TRANSFER_RECEIVED(4);

    private final int code;

    TransactionType(final int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public static TransactionType valueOf(final int code) {
        for(TransactionType value : TransactionType.values()) {
            if(value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("No transaction type with code " + code);
    }

}
