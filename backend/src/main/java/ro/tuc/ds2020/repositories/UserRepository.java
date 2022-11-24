package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    List<Users> findByName(String name);
    Optional<Users> findById(UUID user);
    Optional<Users> findUsersByMailAndPassword(String mail, String password);
    Optional<Users> findUsersByMail(String mail);
}
