package in.cts.budgetanalysis.txns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cts.budgetanalysis.txns.entity.AccountHolder;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long>{

}
