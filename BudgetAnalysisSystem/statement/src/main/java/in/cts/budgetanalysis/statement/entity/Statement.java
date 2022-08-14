package in.cts.budgetanalysis.statement.entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Statement {

	private AccountHolder profile;
	private Set<Txn> txns;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Double totalCredit;
	private Double totalDebit;
	private Double statementBalance;

}
