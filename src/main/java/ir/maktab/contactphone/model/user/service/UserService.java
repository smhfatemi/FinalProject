package ir.maktab.contactphone.model.user.service;

import ir.maktab.contactphone.model.user.User;
import ir.maktab.contactphone.model.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        this.userRepository.save(user);
    }

    public Optional<User> readById(Long id){
        return this.userRepository.findById(id);
    }

    public void update(User user){
        this.userRepository.save(user);
    }

    public List<User> readAll(){
        return this.userRepository.findAll();
    }

    public void deleteById(Long id){
        this.userRepository.deleteById(id);
    }
}
