package c2G.mobile.api.objekts;

/**
 * @author brought to you by 1st
 *
 */
public class Account {

	String accountId;
	String description;
	
	public Account(String accountId, String description) {
		this.accountId = accountId;
		this.description = description;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
