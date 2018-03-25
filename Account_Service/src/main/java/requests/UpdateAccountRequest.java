package requests;

import models.Account;

/**
 * Created by bradleyw on 24/03/2018.
 */
public class UpdateAccountRequest extends AccountServiceRequest {
    private Account updateAccount;

    public UpdateAccountRequest(Long accountId, Account updatedAccount) {
        super(accountId);
        this.setUpdateAccount(updatedAccount);
    }

    public Account getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(Account updateAccount) {
        this.updateAccount = updateAccount;
    }
}
