package com.inventory.security;

import com.inventory.entity.SystemAccount;
import com.inventory.enums.UserRole;
import com.inventory.repository.SystemAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final SystemAccountRepository accountRepository;
    
    public CustomUserDetailsService(SystemAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SystemAccount account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        UserRole role = UserRole.fromInt(account.getRole());
        return new CustomUserDetails(account, role);
    }
}