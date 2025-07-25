package accounts.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accounts.entities.Account;
import accounts.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class RestAccountController {
	
	private AccountService accountService;
	
	
	public RestAccountController(AccountService accountService) {
		
		this.accountService = accountService;

	}
	
	@GetMapping
	public Iterable<Account> getAccounts(){
		return accountService.findAll();
	}
	
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable Long id)throws Exception{		
		return accountService.findById(id);
	}
	
	
	@PostMapping
	public	Account postAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable long id) {
		accountService.deleteAccount(id);
	}
	
	@PutMapping("/{id}")
	public Account putAccount(@PathVariable long id, @RequestBody Account account) {
		return accountService.updateAccount(id, account);
	}
}




