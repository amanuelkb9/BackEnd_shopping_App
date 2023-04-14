package edu.miu.shopmartbackend.repo;

import edu.miu.shopmartbackend.model.Address;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long > {
    @Query(value = "select u from User u where u.username=:username")
    Optional<User> findByUsername(String username);

    @Query("select u from User u join u.roles as r where r.role=:role")
    List<User> findAllByRole(String role);

    User getUserById(long id);
@Query(value = "select a from User u join u.billingAddress a where u.id=:id ")
    List<Address> getBuyerBillingAddress( long id);

    @Query(value = "select a from User u join u.shippingAddress a where u.id=:id ")
    public List<Address> getBuyerShippingAddress(long id);

}
