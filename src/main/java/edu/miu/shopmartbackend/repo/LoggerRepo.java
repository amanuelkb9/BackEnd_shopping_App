package edu.miu.shopmartbackend.repo;

import edu.miu.shopmartbackend.model.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggerRepo extends JpaRepository<Logger, Long> {


}
