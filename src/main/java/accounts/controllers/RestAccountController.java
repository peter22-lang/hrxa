package accounts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;
import accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Account Controller", description = "This REST controller provides services to manage accounts in the Account Application")
public class RestAccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all accounts available in the application")
	public Iterable<Account> getAccounts() {
		return accountService.getAccounts();
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides account details for the supplied account id  available in the application")
	public Account getAccountById(@PathVariable Long id) throws Exception {
		return accountService.getAccountById(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(summary = "Creates a new account in the application")
	public Account postAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes the  account details for the supplied account id from the application")
	public void deleteAccount(@PathVariable long id) {
		accountService.deleteAccountById(id);
	}
	
	@DeleteMapping
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Deletes all accounts details from the application")
	public void deleteAccounts() {
		accountService.deleteAccounts();
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(summary = "Updates the account details in the application for the supplied account id")
	public Account putAccount(@PathVariable long id, @RequestBody Account account) {
		return accountService.updateAccount(id, account);
	}

	@GetMapping("/accountType/{accountType}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides account details for the supplied account id  available in the application")
	public List<Account> getAccountsByType(@PathVariable AccountType accountType) {
		return accountService.getAccountsByType(accountType);
	}

	@GetMapping("/firstName/{firstName}")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides account details for the supplied account id  available in the application")
	public List<Account> getAccountsByFirstName(@PathVariable String firstName) {
		return accountService.getAccountsByFirstName(firstName);
	}

	@GetMapping("/paged")
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(summary = "Provides all accounts in paged format with 5 account for each page")
	public Page<Account> getAllAccountsPaged() {
		return accountService.getAccountsPaged();
	}
}
