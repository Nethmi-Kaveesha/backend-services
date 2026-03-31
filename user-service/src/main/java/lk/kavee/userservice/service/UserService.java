package lk.kavee.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lk.kavee.userservice.entity.User;
import lk.kavee.userservice.repository.UserRepository;
import lk.kavee.userservice.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added successfully";
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }

    public User validateAndGetUser(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(null);
    }
}
