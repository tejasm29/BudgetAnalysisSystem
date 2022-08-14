package in.cts.budgetanalysis.profiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cts.budgetanalysis.profiles.entity.AccountHolder;
import in.cts.budgetanalysis.profiles.exception.BadProfileException;
import in.cts.budgetanalysis.profiles.repository.AccountHolderRepo;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo ahRepository;

	@Override
	public boolean existById(Long id) throws BadProfileException {

		return ahRepository.existsById(id);

	}

	@Override
	public AccountHolder getById(Long id) throws BadProfileException {
		return ahRepository.findById(id).orElse(null);
	}

	@Override
	public AccountHolder add(AccountHolder accountHolder) throws BadProfileException {
		if (accountHolder.getAhId() != null && ahRepository.existsById(accountHolder.getAhId()))
			throw new BadProfileException("Record Exists Already");
		return ahRepository.save(accountHolder);
	}

	@Override
	public AccountHolder modify(AccountHolder accountHolder) throws BadProfileException {
		if (accountHolder.getAhId() == null || !ahRepository.existsById(accountHolder.getAhId()))
			throw new BadProfileException("Record Does Not Exists Hence Can Not Be Modified");
		return null;
	}

}
