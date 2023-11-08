package com.haykz.GymCRM.validation;

import com.haykz.GymCRM.exception.AuthenticationException;
import com.haykz.GymCRM.model.User;
import com.haykz.GymCRM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public void authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordMatches(user, password)) {
            throw new AuthenticationException("Invalid credentials");
        }
    }

    private boolean passwordMatches(User user, String password) {
        // Later implement password hashing and matching logic
        return password.equals(user.getPassword());
    }
}
