import br.com.compass.entities.Account;
import br.com.compass.entities.enums.AccountType;
import br.com.compass.services.AccountConnection;

import java.time.LocalDate;
import java.util.Scanner;


public class Test {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1 para salvar no banco de dados - 2 pra sair da app");
        int opcao  = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                AccountConnection connection = new AccountConnection();
                Account account_type1 = new Account(
                        "Caio",
                        LocalDate.of(2002, 4, 23),
                        "012345678901",
                        "63091245678",
                        AccountType.CHECKING_ACCOUNT);

                Account account_type2 = new Account(
                        "Roberto",
                        LocalDate.of(2004, 9, 26),
                        "013349818901",
                        "61091234578",
                        AccountType.SAVINGS_ACCOUNT);
                Account account_type3 = new Account(
                        "Lucas",
                        LocalDate.of(1990, 2, 11),
                        "592390158901",
                        "9499120948",
                        AccountType.CHECKING_ACCOUNT);

                connection.save(account_type1);
                connection.save(account_type2);
                connection.save(account_type3);
                break;
        }
    }

}
