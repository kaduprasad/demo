package spring.demo.repo;

import spring.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//                                CrudRepository< AnyEntity, ID(primary key type) >
public interface UserRepo extends CrudRepository<User, Integer> {
    // CrudRepository, which is a Spring Data JPA interface providing basic CRUD operations (save, delete, findById, etc.)
    // without needing to write SQL queries.

    /**
     * This method is a custom query method. Even though you didn’t provide an implementation,
     * Spring Data JPA will automatically create it based on its name.
     * findByEmail is a query derivation method, meaning Spring Data JPA reads it as "find a User by their email."
     *  It matches the method name to the email field in your User entity.
     */
    Optional<User> findByEmail(String email);

}
