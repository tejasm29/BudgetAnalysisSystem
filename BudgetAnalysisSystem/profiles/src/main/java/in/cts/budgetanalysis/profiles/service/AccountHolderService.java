package in.cts.budgetanalysis.profiles.service;

import in.cts.budgetanalysis.profiles.entity.AccountHolder;
import in.cts.budgetanalysis.profiles.exception.BadProfileException;

public interface AccountHolderService {

	boolean existById(Long id) throws BadProfileException;

	AccountHolder getById(Long id) throws BadProfileException;

	AccountHolder add(AccountHolder accountHolder) throws BadProfileException;

	AccountHolder modify(AccountHolder accountHolder) throws BadProfileException;

}
