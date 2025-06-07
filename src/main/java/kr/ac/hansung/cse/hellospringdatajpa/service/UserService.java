package kr.ac.hansung.cse.hellospringdatajpa.service;

import kr.ac.hansung.cse.hellospringdatajpa.entity.Role;
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;
import kr.ac.hansung.cse.hellospringdatajpa.repo.RoleRepository;
import kr.ac.hansung.cse.hellospringdatajpa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ✅ 회원 등록 메서드
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> role = roleRepository.findByName(user.getRoleString()); // 🔸 "ROLE_USER" or "ROLE_ADMIN"
        Set<Role> roles = new HashSet<>();
        role.ifPresent(roles::add);

        user.setRoles(roles);
        userRepository.save(user);
    }

    // ✅ 전체 사용자 목록 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
