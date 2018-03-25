package handlers;

import accountDao.AccountDaoInterface;
import interfaces.SynergyRequestHandler;

import models.Account;
import requests.CreateAccountRequest;
import requests.LoginAccountRequest;
import responses.CreateAccountResponse;
import responses.LoginAccountResponse;
import requests.UpdateAccountRequest;
import responses.UpdateAccountResponse;
import sessions.SessionsService;

import java.util.Optional;

/**
 * Created by bradleyw on 24/03/2018.
 */

public class AccountRequestHandler implements SynergyRequestHandler{

    private SessionsService sessionsService;
    private AccountDaoInterface accountDao;

    public AccountRequestHandler() {
        setSessionsService(new SessionsService());
    }

    public CreateAccountResponse handleCreateAccountRequest(CreateAccountRequest createAccountRequest) {
        return null;
    }

    public UpdateAccountResponse handleUpdateAccountRequest(UpdateAccountRequest updateAccountRequest) {
        return null;
    }

    public LoginAccountResponse handleLoginRequest(LoginAccountRequest loginAccountRequest) {
        LoginAccountResponse loginAccountResponse = new LoginAccountResponse(loginAccountRequest);
        if (getSessionsService().isUserCurrentlyLoggedIn(loginAccountRequest.getAccountId())) {
            loginAccountResponse.setMessage("That user is already currently logged in");
            loginAccountRequest.setLoginSuccessful(false);
            return loginAccountResponse;
        }
        Optional<Account> accountOptional = getAccountDao().getAccountById(loginAccountRequest.getAccountId());
        if (!accountOptional.isPresent())
        {
            loginAccountResponse.setMessage("That user does not exist");
            loginAccountRequest.setLoginSuccessful(false);
            return loginAccountResponse;
        }
        loginAccountRequest.setLoginSuccessful(true);
        getSessionsService().loginUser(accountOptional.get());
        return loginAccountResponse;
    }

    public SessionsService getSessionsService() {
        return sessionsService;
    }

    public void setSessionsService(SessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    public AccountDaoInterface getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDaoInterface accountDao) {
        this.accountDao = accountDao;
    }
}
