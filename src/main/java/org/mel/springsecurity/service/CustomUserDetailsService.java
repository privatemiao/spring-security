package org.mel.springsecurity.service;

import org.mel.springsecurity.dto.CustomUserDetails;
import org.mel.springsecurity.entity.Role;
import org.mel.springsecurity.entity.User;
import org.mel.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(loginName);
        if (user == null){
            throw new UsernameNotFoundException(String.format("No user present with username: %s", loginName));
        }

        Set<Role> roles = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        for (Role role : roles){
            roleNames.add(role.getName());
        }

        return new CustomUserDetails(user, roleNames);
    }
}
