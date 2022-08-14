package in.cts.budgetanalysis.txns.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "txns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Txn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long txnId;
	private String header;
	private Double amount;
	@Enumerated(EnumType.STRING)
	private TxnType type;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dateOfTransaction;
	@ManyToOne
	@JoinColumn(name = "ahid")
	@JsonProperty(access = Access.WRITE_ONLY) // ensures that the value is accepted via setter but not sent to client.
	private AccountHolder holder;

}
