package in.cts.budgetanalysis.txns.service;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cts.budgetanalysis.txns.entity.AccountHolder;
import in.cts.budgetanalysis.txns.repository.AccountHolderRepository;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepository ahRepository;

	@Autowired
	private ProfilesClient profileClient;

	@Override
	public AccountHolder getById(Long ahId) {
		AccountHolder accountHolder = ahRepository.findById(ahId).orElse(null);
		if (accountHolder == null && profileClient.checkForAccountHolderById(ahId)) {
			accountHolder = new AccountHolder(ahId, 0.0, new TreeSet<>());
		}
		return accountHolder;
	}

	@Override
	public AccountHolder save(AccountHolder accountHolder) {

		return ahRepository.save(accountHolder);
	}

	@Override
	public double getCurrentBalance(Long ahId) {
		AccountHolder accountHolder = ahRepository.findById(ahId).orElse(null);
		return accountHolder == null ? 0.0 : accountHolder.getCurrentBalance();
	}

}
