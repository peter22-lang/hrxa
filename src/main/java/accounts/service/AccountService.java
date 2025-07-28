package accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;
import accounts.repositories.AccountRepository;

@Service
public class AccountService {
	
	private AccountRepository accountRepository;
	
	Pageable firstPageWithTwo = PageRequest.of(0,2,Sort.by("firstName"));
	Pageable secondPageWithFour = PageRequest.of(1, 4);
	
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
	
	public Page<Account> findAllAccountsPaged(){
		return accountRepository.findAll(firstPageWithTwo);
	}
	
	public List<Account> findAccountsByType(AccountType type){
		return accountRepository.findAccountsByType(type, firstPageWithTwo);
	}
	
	public List<Account> findAccountsByFirstName(String firstName){
		return accountRepository.findAccountsByFirstName(firstName, firstPageWithTwo);
	}

}
