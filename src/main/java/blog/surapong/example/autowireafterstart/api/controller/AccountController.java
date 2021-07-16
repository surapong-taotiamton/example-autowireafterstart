package blog.surapong.example.autowireafterstart.api.controller;

import blog.surapong.example.autowireafterstart.entity.Account;
import blog.surapong.example.autowireafterstart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/get-config")
    public String getConfig() {
        AccountService accountService = new AccountService();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(accountService);
        return accountService.getAccountConfig();
    }

    @GetMapping("/test-create")
    public String testCreate() {
        AccountService accountService = new AccountService();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(accountService);
        Account account = accountService.createAccount("test-create-id", "test-create-name");
        return account.getAccountId() + " : " + account.getAccountName();
    }

    @GetMapping("/test-transactional-error-case")
    public String testCreateErrorCase() {
        AccountService accountService = new AccountService();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(accountService);
        Account account = accountService.testTransactional("test-transactional-create-id", "test-transactional-create-id");
        return account.getAccountId() + " : " + account.getAccountName();
    }

    @GetMapping("/test-error-transactional-success-case")
    public String testCreateSuccessCase() {
        AccountService accountService = new AccountService();
        applicationContext.getAutowireCapableBeanFactory().autowireBean(accountService);
        accountService = (AccountService) applicationContext.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(accountService, null);
        Account account = accountService.testTransactional("test-transactional-create-id-complete", "test-transactional-create-id-complete");
        return account.getAccountId() + " : " + account.getAccountName();
    }
}
