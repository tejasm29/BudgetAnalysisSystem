package in.cts.budgetanalysis.txns.service;

import in.cts.budgetanalysis.txns.entity.AccountHolder;

public interface AccountHolderService {

	AccountHolder getById(Long ahId);

	AccountHolder save(AccountHolder accountHolder);

	double getCurrentBalance(Long ahId);

}
