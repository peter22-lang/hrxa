package accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Account {
	
	public static enum AccountType{
		BASIC,FIXED_DEPOSIT,CURRENT,SAVINGS
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private AccountType accountType; 
	
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public Account() {
	}

	public Account(Long id, String firstName, String lastName,AccountType accoountType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accoountType;
	}
	/*
	 * public Account(String firstName,String lastName) {
	 * this(UUID.randomUUID().toString(),firstName,lastName); }
	 */

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
