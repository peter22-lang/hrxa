package accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;
import accounts.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	Pageable firstPageWithTwo = PageRequest.of(0,2,Sort.by("firstName"));
	Pageable secondPageWithFour = PageRequest.of(1, 4);
	Pageable pageByFive = PageRequest.of(0, 5,Sort.by("firstName"));
	

	public Account getAccountById(long id) {
		Optional<Account> acc = accountRepository.findById(id);
		return acc.get();
	}
	
	public List<Account> getAccounts(){
		return accountRepository.findAll();
	}
	
	@Transactional
	public Account createAccount(Account account) {
			return accountRepository.save(account);
	}
	@Transactional
	public void deleteAccountById(long id) {
		accountRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteAccounts() {
		accountRepository.deleteAll();
	}
	
	@Transactional
	public Account updateAccount(long id, Account account) {
		return accountRepository.findById(id)
				.map(acc -> {
					acc.setFirstName(account.getFirstName());
					acc.setLastName(account.getLastName());
					acc.setAccountType(account.getAccountType());
					return accountRepository.save(acc);
				})
				.orElseGet(( ) -> {
					return accountRepository.save(account);
				});
	}
	
	public Page<Account> getAccountsPaged(){
		return accountRepository.findAll(pageByFive);
	}
	
	public List<Account> getAccountsByType(AccountType accountType){
		return accountRepository.findAccountsByAccountType(accountType, firstPageWithTwo);
	}
	
	public List<Account> getAccountsByFirstName(String firstName){
		return accountRepository.findAccountsByFirstName(firstName, firstPageWithTwo);
	}

}
