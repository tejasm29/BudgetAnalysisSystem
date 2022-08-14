package in.cts.budgetanalysis.txns.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cts.budgetanalysis.txns.entity.Txn;

@Repository
public interface TxnsRepo extends JpaRepository<Txn, Long> {

	@Query("SELECT t FROM Txn t WHERE t.holder.ahId = :ahid AND t.dateOfTransaction BETWEEN :from AND :to")
	List<Txn> getAllTxn(Long ahid, LocalDate from, LocalDate to);

}
