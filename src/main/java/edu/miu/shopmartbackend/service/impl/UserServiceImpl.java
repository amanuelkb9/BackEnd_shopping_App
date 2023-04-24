package edu.miu.shopmartbackend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.PaymentIntent;
import edu.miu.shopmartbackend.model.Role;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.model.dto.UserDto;
import edu.miu.shopmartbackend.model.dto.UsernamePassDto;
import edu.miu.shopmartbackend.repo.RoleRepo;
import edu.miu.shopmartbackend.repo.UserRepo;
import edu.miu.shopmartbackend.service.PaymentService;
import edu.miu.shopmartbackend.service.UserService;
import edu.miu.shopmartbackend.util.ListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    ListMapper<User, UserDto> listMapperToDto;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PaymentService paymentService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepo.findByUsername(username).get();
        log.info("User found in the database: {}", username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        myUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));

        return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), authorities);
    }


    @Override
    public void addUser(UsernamePassDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(modelMapper.map(user, User.class));
    }

    @Override
    public void deleteUser(long id) {
     userRepo.deleteById(id);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        log.info("adding role to user.......");
        User user = userRepo.findByUsername(username).get();

        Role role1 = roleRepo.findByRole(role);
        user.getRoles().add(role1);
    }

    @Override
    public UserDto approveSeller(long id) {
        User seller = modelMapper.map(userRepo.getUserById(id), User.class);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setName(seller.getFirstname());
        paymentDto.setAmount(20000.00);
        paymentDto.setBuyer_id(id);
        paymentDto.setCurrency("usd");
        paymentDto.setCardNumber("4242424242424242");
        paymentDto.setExp_month(12);
        paymentDto.setExp_year(2025);
        paymentDto.setEmail(seller.getEmail());
        paymentDto.setType("card");
        try {
            PaymentIntent paymentIntent = paymentService.handlePayment(paymentDto);
            if (("succeeded".equals(paymentIntent.getStatus()))) {
                seller.setAproved(true);
            } else {
                seller.setAproved(false);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return modelMapper.map(userRepo.save(seller), UserDto.class);    }



    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256("My53cr3tT0K3N^[a-zA-Z0-9._]^+$MyR@nd0m5tr1ng".getBytes());

                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userRepo.findByUsername(username).get();

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
//                response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @Override
    public UserDto  registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            User user = modelMapper.map(userDto, User.class);

            userRepo.save(user);
            return userDto;
        }


    @Override
    public UserDto approveBuyer(long id) {
        User buyer = userRepo.getUserById(id);
        buyer.setAproved(true);
        return modelMapper.map(userRepo.save(buyer), UserDto.class);
    }
}

