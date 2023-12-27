package com.example.tlgaskapp.controllers;

import com.example.tlgaskapp.DTO.AuthDTO;
import com.example.tlgaskapp.DTO.UserRequestDTO;
import com.example.tlgaskapp.entities.UserEntity;
import com.example.tlgaskapp.security.JwtTokenProvider;
import com.example.tlgaskapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody UserRequestDTO loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth); //auth objesini alır ve user detail'e göre token oluşur
        UserEntity userEntity = userService.getOneUserByUserName(loginRequest.getUserName());
        AuthDTO authDTO = new AuthDTO();
        authDTO.setMessage("Bearer " + jwtToken);
        authDTO.setUserId(userEntity.getId());
        return authDTO;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthDTO> register(@RequestBody UserRequestDTO userRequestDTO){
        AuthDTO authDTO = new AuthDTO();
        if(userService.getOneUserByUserName(userRequestDTO.getUserName()) != null){
            authDTO.setMessage("Username already in use!");
            return new ResponseEntity<>(authDTO, HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userRequestDTO.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userService.saveOneUser(userEntity);
        authDTO.setMessage("User Successfully Registered...");
        return new ResponseEntity<>(authDTO, HttpStatus.CREATED);
    }
}
