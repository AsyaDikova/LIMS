package adk.lims.user.abstractuser.service;

import adk.lims.user.abstractuser.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(User user);

    User getCurrentUser();
}
