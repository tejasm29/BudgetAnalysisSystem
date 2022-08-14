package in.cts.budgetanalysis.profiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cts.budgetanalysis.profiles.entity.AccountHolder;
import in.cts.budgetanalysis.profiles.exception.BadProfileException;
import in.cts.budgetanalysis.profiles.service.AccountHolderService;

@RestController
@RequestMapping("/")
public class AccountHolderController {

	@Autowired
	private AccountHolderService ahService;

	@GetMapping("/{ahid}")
	public ResponseEntity<AccountHolder> getAccountHolderById(@PathVariable("ahid") Long ahId)
			throws BadProfileException {

		AccountHolder accountHolder = ahService.getById(ahId);

		return accountHolder == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(accountHolder);
	}

	@GetMapping("/{ahid}/exists")
	public ResponseEntity<Boolean> existsAccountHolderById(@PathVariable("ahid") Long ahId) throws BadProfileException {
		return ResponseEntity.ok(ahService.existById(ahId));

	}

	@PostMapping
	public ResponseEntity<AccountHolder> addAccountHolder(@RequestBody AccountHolder accountHolder)
			throws BadProfileException {
		return new ResponseEntity<AccountHolder>(ahService.add(accountHolder), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<AccountHolder> modifyAccountHolder(@RequestBody AccountHolder accountHolder)
			throws BadProfileException {

		return new ResponseEntity<AccountHolder>(ahService.modify(accountHolder), HttpStatus.ACCEPTED);
	}
}
