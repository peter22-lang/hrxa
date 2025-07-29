
  package accounts.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;

@Service
public interface AccountService {
		Account getAccountById(long id);
		
		List<Account> getAccounts();
		
		Account createAccount(Account account);
		
		void deleteAccountById(long id);		
		
		void deleteAccounts();
		
		Account updateAccount(long id, Account account);
		
		Page<Account> getAccountsPaged();
		
		List<Account> getAccountsByType(AccountType accountType);
		
		List<Account> getAccountsByFirstName(String firstName);

  }
 