package jpa.main;

import jpa.config.Config;
import jpa.entity.AccountEntity;
import jpa.repository.AccountRepository;
import jpa.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class MainTestTransaction {
    static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    static AccountRepository accountRepository = (AccountRepository) context.getBean("accountRepository");
    static AccountService accountService = (AccountService) context.getBean("accountService");

    public static void main(String[] args) throws Exception {

        createNewAccount();
//        accountService.transferMoneyRollback(1, 2, 150);
//        accountService.transferMoneyWithoutRollback(2, 1, 150);
        accountService.transferMoneyAnnotation(2, 1, 100);
    }

    public static void createNewAccount() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setOwnerName("owner-1");
        accountEntity.setBalance(100);
        accountEntity.setLooked(false);
        accountEntity.setAccessTime(LocalDate.parse("2019-03-18"));

        AccountEntity result1 = accountRepository.save(accountEntity);

        AccountEntity accountEntity2 = new AccountEntity();

        accountEntity2.setOwnerName("owner-2");
        accountEntity2.setBalance(0);
        accountEntity2.setLooked(false);
        accountEntity2.setAccessTime(LocalDate.parse("2019-03-18"));

        AccountEntity result2 = accountRepository.save(accountEntity2);


    }
}

