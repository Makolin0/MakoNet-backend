package MakoNetbackend.repositories;

import MakoNetbackend.models.database.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {
    Optional<UserDB> findByUsername(String username);
//    Optional<UserDB> findByEmailIgnoreCase(String email);
}
