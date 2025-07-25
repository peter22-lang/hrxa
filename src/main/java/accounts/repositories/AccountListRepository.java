package accounts.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;

@Repository
public interface AccountListRepository extends ListCrudRepository<Account,Long>{
	
	List <Account> findAccountsByType(AccountType type);

}
