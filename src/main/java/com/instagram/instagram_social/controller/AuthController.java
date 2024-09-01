package com.instagram.instagram_social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagram_social.config.JWTProvider;
import com.instagram.instagram_social.model.User;
import com.instagram.instagram_social.repository.UserRepository;
import com.instagram.instagram_social.request.LoginRequest;
import com.instagram.instagram_social.response.AuthResponse;
import com.instagram.instagram_social.service.CustomUserDetailService;
import com.instagram.instagram_social.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // I'm adding  this camment to identify to how git will work
    

    // @Autowired
    // private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    // /auth/signup
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist != null){
            throw new Exception("Email olready used with another user.");
        }

        User newUser = new User();
        
        // newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setGender(user.getGender());

        User saveUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());

        String token = JWTProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Registered Successfully");

        return res;
    }

    @PostMapping("/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = JWTProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token, "Login Successfully");

        return res;
    }

    private Authentication authenticate(String email, String password) {
        
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid UserName");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
           throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}