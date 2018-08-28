package adk.lims.user.abstractuser.repository;

import adk.lims.user.abstractuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
