package accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Account {
	
	public static enum AccountType{
		BASIC,CURRENT,FIXED_DEPOSIT,SAVINGS
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private AccountType type; 
	
	public Account() {
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public Account(Long id, String firstName, String lastName,AccountType type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
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
