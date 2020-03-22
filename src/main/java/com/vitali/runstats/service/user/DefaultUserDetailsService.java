package com.vitali.runstats.service.user;

import com.vitali.runstats.dto.UserDto;
import com.vitali.runstats.entity.User;
import com.vitali.runstats.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserDetailsService implements UserDetailsService, UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public DefaultUserDetailsService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + login);
        }
        return buildUserDetails(user);
    }

    @Override
    public UserDto save(UserDto user) {
        return modelMapper.map(
                userRepo.save(modelMapper.map(user, User.class)),
                UserDto.class);
    }

    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private UserDetails buildUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(Collections.singletonList(user.getRole().getRole()))
        );
    }

}