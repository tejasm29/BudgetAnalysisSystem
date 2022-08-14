package in.cts.budgetanalysis.txns.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cts.budgetanalysis.txns.entity.AccountHolder;
import in.cts.budgetanalysis.txns.entity.Txn;
import in.cts.budgetanalysis.txns.entity.TxnType;
import in.cts.budgetanalysis.txns.exception.BadTxnException;
import in.cts.budgetanalysis.txns.repository.TxnsRepo;

@Service
public class TxnsServiceImpl implements TxnsService {

	@Autowired
	private AccountHolderService ahService;

	@Autowired
	private TxnsRepo txnRepository;

	@Override
	public Txn saveTransaction(Txn txn) throws BadTxnException {
		Long ahId = txn.getHolder().getAhId();

		AccountHolder accountHolder = ahService.getById(ahId);

		if (accountHolder == null) {
			throw new BadTxnException("Txn can not be attached to an account that does not exist");
		}

		txn.setHolder(accountHolder);

		accountHolder
				.setCurrentBalance(txn.getType() == TxnType.CREDIT ? accountHolder.getCurrentBalance() + txn.getAmount()
						: accountHolder.getCurrentBalance() - txn.getAmount());
		ahService.save(accountHolder);
		txnRepository.save(txn);
		return txn;
	}

	@Override
	public Txn modifyTransaction(Txn txn) throws BadTxnException {
		Long ahId = txn.getHolder().getAhId();

		AccountHolder accountHolder = ahService.getById(ahId);

		if (accountHolder == null) {
			throw new BadTxnException("Txn can not be attached to an account that does not exist");
		}

		txn.setHolder(accountHolder);

		Txn getTxn = txnRepository.findById(txn.getTxnId()).orElse(null);

		if (txn.getAmount() != getTxn.getAmount()) {

			Double difference = txn.getAmount() - getTxn.getAmount();

			if (txn.getAmount() < getTxn.getAmount()) {
				accountHolder.setCurrentBalance(
						txn.getType() == TxnType.CREDIT ? accountHolder.getCurrentBalance() - difference
								: accountHolder.getCurrentBalance() + Math.abs(difference));
			} else {

				accountHolder.setCurrentBalance(
						txn.getType() == TxnType.CREDIT ? accountHolder.getCurrentBalance() + difference
								: accountHolder.getCurrentBalance() - Math.abs(difference));
			}

		}
		ahService.save(accountHolder);
		txnRepository.save(txn);
		return txn;
	}

	@Override
	public void deleteTransactionById(Long txnId) throws BadTxnException {

		Txn txn = txnRepository.findById(txnId).orElse(null);
		if (txn == null) {
			throw new BadTxnException("No Such Txn To Delete");
		}

		AccountHolder accountHolder = txn.getHolder();

		accountHolder
				.setCurrentBalance(txn.getType() == TxnType.CREDIT ? accountHolder.getCurrentBalance() - txn.getAmount()
						: accountHolder.getCurrentBalance() + txn.getAmount());

		ahService.save(accountHolder);
		txnRepository.delete(txn);

	}

	@Override
	public List<Txn> getAllTxns(Long ahid, LocalDate from, LocalDate to) {

		return txnRepository.getAllTxn(ahid, from, to);

	}

}
