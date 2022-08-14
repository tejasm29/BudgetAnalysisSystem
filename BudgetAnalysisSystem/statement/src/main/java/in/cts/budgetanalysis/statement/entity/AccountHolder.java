package in.cts.budgetanalysis.statement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolder {

	private Long ahId;
	private String firstName;
	private String lastName;
	private String mailId;
	private String mobile;
	private Double currentBalance;

}
