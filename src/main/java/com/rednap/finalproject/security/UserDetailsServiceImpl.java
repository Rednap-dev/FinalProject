package com.rednap.finalproject.security;

import com.rednap.finalproject.security.UserDetailsImpl;
import com.rednap.finalproject.model.entity.UserEntity;
import com.rednap.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        return new UserDetailsImpl(optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
