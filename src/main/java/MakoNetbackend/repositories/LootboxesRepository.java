package MakoNetbackend.repositories;

import MakoNetbackend.models.database.LootboxesDB;
import MakoNetbackend.models.database.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LootboxesRepository extends JpaRepository<LootboxesDB, Long> {
}
