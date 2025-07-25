package accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import accounts.entities.Account;
import accounts.repositories.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account findById(long id) {
		Optional<Account> acc = accountRepository.findById(id);
		return acc.get();
	}
	
	public List<Account> findAll(){
		return accountRepository.findAll();
	}
	
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}
	
	public void deleteAccount(long id) {
		accountRepository.deleteById(id);
	}
	
	public Account updateAccount(long id, Account account) {
		return accountRepository.findById(id)
				.map(acc -> {
					acc.setFirstName(account.getFirstName());
					acc.setLastName(account.getLastName());
					acc.setType(account.getType());
					return accountRepository.save(acc);
				})
				.orElseGet(( ) -> {
					return accountRepository.save(account);
				});
	}

}
