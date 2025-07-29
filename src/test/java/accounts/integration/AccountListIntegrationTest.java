package accounts.integration;




import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import accounts.entities.Account;
import accounts.entities.Account.AccountType;
import accounts.repositories.AccountListRepository;



@SpringBootTest
@RunWith(value=Parameterized.class)
public class AccountListIntegrationTest {

	@Autowired
	private AccountListRepository accListRepository;
	
	
	@Test
	public void testFindBooksByType() {		
		Account acc1 = new Account(13L, "James","Smith",AccountType.BASIC);
		Account acc2 = new Account(14L,"Sue","Parker",AccountType.SAVINGS);
		Account acc3 = new Account(15L,"David","Blane",AccountType.SAVINGS);
		accListRepository.saveAll(Arrays.asList(acc1, acc2,acc3));
		
		List<Account> accounts = accListRepository.findAccountsByAccountType(AccountType.SAVINGS);
		assertEquals(2,accounts.size());
		
	}
	
}
