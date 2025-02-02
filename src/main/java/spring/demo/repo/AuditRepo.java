package spring.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.demo.entity.Audit;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuditRepo extends CrudRepository<Audit, UUID> {

//    Optional<Audit> findByEmail(String email);

}
