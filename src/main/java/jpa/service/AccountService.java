package jpa.service;

import jpa.entity.AccountEntity;
import jpa.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private JpaTransactionManager transactionManager;

    public AccountService(JpaTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

   public void transferMoneyWithoutRollback(int sourceAccountId, int targetAccountId, double amount) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            AccountEntity sourceAccount = accountRepository.findById(sourceAccountId).get();
            AccountEntity targetAccount = accountRepository.findById(targetAccountId).get();

            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            accountRepository.save(sourceAccount);
            if (sourceAccount.getBalance() < 0) {
                throw new Exception("amount to transfer more than balance.");
            }

            targetAccount.setBalance(targetAccount.getBalance() + amount);
            accountRepository.save(targetAccount);

            transactionManager.commit(status);
        } catch (Exception exception) {
            transactionManager.rollback(status);
            throw new RuntimeException(exception);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void transferMoneyRollback(int sourceAccountId, int targetAccountId, double amount) throws Exception{
            AccountEntity sourceAccount = accountRepository.findById(sourceAccountId).get();
            AccountEntity targetAccount = accountRepository.findById(targetAccountId).get();

            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            accountRepository.save(sourceAccount);
            if (sourceAccount.getBalance() < 0) {
                throw new Exception("amount to transfer more than balance.");
            }

            targetAccount.setBalance(targetAccount.getBalance() + amount);
            accountRepository.save(targetAccount);
    }

    @Transactional
    public void transferMoneyAnnotation(int sourceAccountId, int targetAccountId, double amount) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        AccountEntity sourceAccount = accountRepository.findById(sourceAccountId).get();
        AccountEntity targetAccount = accountRepository.findById(targetAccountId).get();

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        accountRepository.save(sourceAccount);

        targetAccount.setBalance(targetAccount.getBalance() + amount);
        accountRepository.save(targetAccount);

    }

}
