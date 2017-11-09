package org.mel.springsecurity;

import org.mel.springsecurity.entity.Role;
import org.mel.springsecurity.entity.User;
import org.mel.springsecurity.repository.RoleRepository;
import org.mel.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Role roleAdmin = roleRepository.save(new Role("Admin", "管理员"));
            Role roleUser = roleRepository.save(new Role("User", "用户"));
            Role roleLeader = roleRepository.save(new Role("Leader", "管理者"));

            System.out.println(roleAdmin);
            System.out.println(roleUser);
            System.out.println(roleLeader);

            User admin = new User("admin", 1);
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleAdmin);
            adminRoles.add(roleUser);
            adminRoles.add(roleLeader);
            admin.setRoles(adminRoles);
            User _admin = userRepository.save(admin);

            User user = new User("user", 1);
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleUser);
            user.setRoles(userRoles);
            userRepository.save(user);

            User leader = new User("leader", 1);
            Set<Role> leaderRoles = new HashSet<>();
            leaderRoles.add(roleLeader);
            leader.setRoles(leaderRoles);
            userRepository.save(leader);

            System.out.println(userRepository.findByLoginName("admin"));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
}
