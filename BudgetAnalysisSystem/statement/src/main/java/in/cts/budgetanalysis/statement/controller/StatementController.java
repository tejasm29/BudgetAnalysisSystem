package in.cts.budgetanalysis.statement.controller;

import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cts.budgetanalysis.statement.entity.Statement;
import in.cts.budgetanalysis.statement.exception.BadStatementException;
import in.cts.budgetanalysis.statement.service.StatementService;

@RestController
@RequestMapping("/")
public class StatementController {

	@Autowired
	private StatementService statementService;

	@GetMapping("/{ahid}/{year}/{month}")
	public ResponseEntity<Statement> fetchMonthlyStatement(@PathVariable("ahid") Long ahId,
			@PathVariable("year") int year, @PathVariable("month") Month month) throws BadStatementException {

		return ResponseEntity.ok(statementService.generateMonthlyStatement(ahId, month, year));

	}

	@GetMapping("/{ahid}/{year}")
	public ResponseEntity<Statement> fetchYearlyStatement(@PathVariable("ahid") Long ahId,
			@PathVariable("year") int year) throws BadStatementException {

		return ResponseEntity.ok(statementService.generateAnnualStatement(ahId, year));
	}

}
