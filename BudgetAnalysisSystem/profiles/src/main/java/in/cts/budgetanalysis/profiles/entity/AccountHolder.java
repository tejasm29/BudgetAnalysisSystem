package in.cts.budgetanalysis.profiles.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ahId;
	private String firstName;
	private String lastName;
	private String mailId;
	private String mobile;

}
