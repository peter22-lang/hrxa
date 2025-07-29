package accounts.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account,Long>,ListCrudRepository<Account,Long> {
	
	List<Account> findAccountsByFirstName(String firstName,Pageable pageable);
	List<Account> findAccountsByAccountType(AccountType accountType, Pageable pageable);

}
