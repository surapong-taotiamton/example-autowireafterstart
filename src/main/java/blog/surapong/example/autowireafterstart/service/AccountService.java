package blog.surapong.example.autowireafterstart.service;


import blog.surapong.example.autowireafterstart.entity.Account;
import blog.surapong.example.autowireafterstart.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${config.account}")
    private String accountConfig;

    public String getAccountConfig() {
        return this.accountConfig;
    }

    public Account createAccount(String accountId, String accountName) {
        return this.accountRepository.save(new Account().setAccountId(accountId).setAccountName(accountName));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Account testTransactional(String accountId, String accountName) {
        Account account = this.accountRepository.save(new Account().setAccountId(accountId).setAccountName(accountName));
        if (true) {
            throw new RuntimeException("Test transactional");
        }
        return account;
    }
}
