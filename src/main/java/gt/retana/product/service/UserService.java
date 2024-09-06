package gt.retana.product.service;

import gt.retana.product.model.User;
import gt.retana.product.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User update(User user) {
        return userRepository.save(user);
    }
    public User findUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }
}