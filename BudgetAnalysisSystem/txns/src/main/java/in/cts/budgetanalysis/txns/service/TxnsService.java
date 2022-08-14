package in.cts.budgetanalysis.txns.service;

import java.time.LocalDate;
import java.util.List;

import in.cts.budgetanalysis.txns.entity.Txn;
import in.cts.budgetanalysis.txns.exception.BadTxnException;

public interface TxnsService {

	Txn saveTransaction(Txn txn) throws BadTxnException;

	void deleteTransactionById(Long txnId) throws BadTxnException;

	List<Txn> getAllTxns(Long ahid, LocalDate from, LocalDate to);

	Txn modifyTransaction(Txn txn) throws BadTxnException;

}
