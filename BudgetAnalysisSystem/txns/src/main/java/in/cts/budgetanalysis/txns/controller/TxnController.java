package in.cts.budgetanalysis.txns.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cts.budgetanalysis.txns.entity.Txn;
import in.cts.budgetanalysis.txns.exception.BadTxnException;
import in.cts.budgetanalysis.txns.service.AccountHolderService;
import in.cts.budgetanalysis.txns.service.TxnsService;

@RestController
@RequestMapping("/")
public class TxnController {

	@Autowired
	private TxnsService txnService;

	@Autowired
	private AccountHolderService ahService;

	@GetMapping("/{ahid}/balance")
	public ResponseEntity<Double> getCurrentBalanceById(@PathVariable("ahid") Long ahId) {
		return new ResponseEntity<Double>(ahService.getCurrentBalance(ahId), HttpStatus.OK);

	}

	@GetMapping("/{ahid}/{from}/{to}")
	public ResponseEntity<List<Txn>> getAllTxnBetweenFromAndTo(@PathVariable("ahid") Long ahid,
			@PathVariable("from")  @DateTimeFormat(iso=ISO.DATE) LocalDate from, @PathVariable("to")  @DateTimeFormat(iso=ISO.DATE) LocalDate to) {

		List<Txn> allTxns = txnService.getAllTxns(ahid, from, to);
		return new ResponseEntity<List<Txn>>(allTxns, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Txn> saveTransaction(@RequestBody Txn txn) throws BadTxnException {
		return new ResponseEntity<Txn>(txnService.saveTransaction(txn), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Txn> modifyTransaction(@RequestBody Txn txn) throws BadTxnException {
		return new ResponseEntity<Txn>(txnService.modifyTransaction(txn), HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/{txnId}")
	public ResponseEntity<Void> deleteTransactionByTxnId(@PathVariable("txnId") Long txnId) throws BadTxnException {
		txnService.deleteTransactionById(txnId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
