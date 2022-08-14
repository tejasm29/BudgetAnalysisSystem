package in.cts.budgetanalysis.statement.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cts.budgetanalysis.statement.entity.AccountHolder;
import in.cts.budgetanalysis.statement.entity.Statement;
import in.cts.budgetanalysis.statement.entity.Txn;
import in.cts.budgetanalysis.statement.entity.TxnType;
import in.cts.budgetanalysis.statement.exception.BadStatementException;

@Service
public class StatementServiceImpl implements StatementService {

	@Autowired
	private ProfilesClient profilesClient;

	@Autowired
	private TxnsClient txnsClient;

	@Override
	public Statement generateMonthlyStatement(long ahId, Month month, int year) throws BadStatementException {
		LocalDate from = LocalDate.of(year, month, 1);
		LocalDate to = from.with(TemporalAdjusters.lastDayOfMonth());
		return generateStatement(ahId, from, to);
	}

	@Override
	public Statement generateAnnualStatement(long ahId, int year) throws BadStatementException {
		LocalDate from = LocalDate.of(year, Month.JANUARY, 1);
		LocalDate to = from.with(TemporalAdjusters.lastDayOfYear());
		return generateStatement(ahId, from, to);
	}

	private Statement generateStatement(long ahId, LocalDate from, LocalDate to) throws BadStatementException {

		// check if account holder is present or not
		if (!profilesClient.doesAccountHolderExists(ahId)) {
			throw new BadStatementException("Statement for a non-existing account can not be generated");
		}

		AccountHolder accountHolder = profilesClient.getAccountHolderById(ahId);
		accountHolder.setCurrentBalance(txnsClient.getCurrentBalance(ahId));

		Set<Txn> allTxns = txnsClient.getAllTxns(ahId, from, to);

		double totalCredits = sumOfAll(allTxns, TxnType.CREDIT);
		double totalDebits = sumOfAll(allTxns, TxnType.DEBIT);
		double statementBalance = totalCredits - totalDebits;

		return new Statement(accountHolder, allTxns, from, to, totalCredits, totalDebits, statementBalance);
	}

	private double sumOfAll(Set<Txn> allTxns, TxnType txnType) {

		return allTxns.stream().filter(t -> t.getType() == txnType).mapToDouble(Txn::getAmount).sum();

	}

}
