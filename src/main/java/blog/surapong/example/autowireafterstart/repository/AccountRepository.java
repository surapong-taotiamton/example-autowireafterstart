package blog.surapong.example.autowireafterstart.repository;

import blog.surapong.example.autowireafterstart.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
