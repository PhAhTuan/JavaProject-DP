package com.inventory.controller;

import com.inventory.dto.*;
import com.inventory.entity.SystemAccount;
import com.inventory.enums.UserRole;
import com.inventory.repository.SystemAccountRepository;
import com.inventory.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Authentication", description = "Login endpoints")
public class AuthController {
    
    private final SystemAccountRepository accountRepository;
    private final JwtUtil jwtUtil;
    
    public AuthController(SystemAccountRepository accountRepository, JwtUtil jwtUtil) {
        this.accountRepository = accountRepository;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/auth")
    @Operation(summary = "Login", description = "Authenticate user and get JWT token")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        SystemAccount account = accountRepository.findByEmail(request.getEmail())
                .orElse(null);
        
        if (account == null || !account.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("PR40101", "Authentication error"));
        }
        
        if (Boolean.FALSE.equals(account.getIsActive())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("PR40301", "Not authorized"));
        }
        
        UserRole role = UserRole.fromInt(account.getRole());
        if (role == UserRole.USER || role == UserRole.UNKNOWN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponse("PR40301", "Not authorized"));
        }
        
        String token = jwtUtil.generateToken(account.getEmail(), role.name().toLowerCase());
        return ResponseEntity.ok(new LoginResponse(token, role.name().toLowerCase()));
    }
}