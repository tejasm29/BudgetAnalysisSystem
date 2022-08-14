package in.cts.budgetanalysis.profiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.cts.budgetanalysis.profiles.entity.AccountHolder;

public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

	
}
