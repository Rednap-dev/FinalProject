package com.rednap.finalproject.service.impl;

import com.rednap.finalproject.model.UserDetailsImpl;
import com.rednap.finalproject.model.entity.UserEntity;
import com.rednap.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(username);
        return new UserDetailsImpl(optionalUserEntity.orElseThrow(() -> new UsernameNotFoundException(username)));
    }
}
