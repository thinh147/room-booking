package com.example.booking.service.imp;

import com.example.booking.config.jwt.UserDetailsImpl;
import com.example.booking.constant.ERole;
import com.example.booking.controller.req.UserRegisterReq;
import com.example.booking.entity.User;
import com.example.booking.repository.UserRepository;
import com.example.booking.service.UserService;
import lombok.SneakyThrows;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public void registerUser(@Valid UserRegisterReq req) throws RuntimeException {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus transaction = transactionManager.getTransaction(definition);
        try {
            Optional<User> userOpt = userRepo.findByUserName(req.getUsername());

            if (userOpt.isPresent())
                throw new MessageDescriptorFormatException(String.format("Email %s already existed", req.getUsername()));
            User user = new User();
            user.setUserName(req.getUsername());
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setEmail(req.getEmail());
            user.setRole(ERole.CUSTOMER.getValue());
            user.setPosition(req.getPosition());
            userRepo.save(user);
            transactionManager.commit(transaction);
        } catch (Exception e) {
            transactionManager.rollback(transaction);
            throw new MessageDescriptorFormatException("Register fail");
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
