package in.cts.budgetanalysis.statement.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Txn {

	private Long txnId;
	private String header;
	private Double amount;
	private TxnType type;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfTransaction;

}
