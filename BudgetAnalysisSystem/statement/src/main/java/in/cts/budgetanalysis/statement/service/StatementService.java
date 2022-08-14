package in.cts.budgetanalysis.statement.service;

import java.time.Month;

import in.cts.budgetanalysis.statement.entity.Statement;
import in.cts.budgetanalysis.statement.exception.BadStatementException;

public interface StatementService {

	Statement generateMonthlyStatement(long ahId, Month month, int year) throws BadStatementException;

	Statement generateAnnualStatement(long ahId, int year) throws BadStatementException;

}
