package in.cts.budgetanalysis.txns.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account_holders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder {

	@Id
	private Long ahId;
	private Double currentBalance;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "holder")
	private Set<Txn> txns;

}
