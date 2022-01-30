package com.unitbv.backend.service.implementation;

import com.unitbv.backend.exception.ResourceAlreadyExistsException;
import com.unitbv.backend.exception.ResourceNotFoundException;
import com.unitbv.backend.factory.JWTGenerator;
import com.unitbv.backend.model.dto.AuthenticationRequest;
import com.unitbv.backend.model.dto.AuthenticationResponse;
import com.unitbv.backend.model.dto.UserDTO;
import com.unitbv.backend.model.entity.UserDO;
import com.unitbv.backend.repository.UserRepository;
import com.unitbv.backend.service.interfaces.UserService;
import com.unitbv.backend.temporary.TemporaryUsers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private MessageSource messageSource;
    private JWTGenerator jwtGenerator;
    private TemporaryUsers temporaryUsers;
    private JavaMailSender mailSender;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Async
    public void sendConfirmationCode(UserDTO user) {
        userRepository.findByEmail(user.getEmail()).ifPresent((userDO) -> {
            throw new ResourceAlreadyExistsException(
                    messageSource.getMessage("api.error.user.already.exists", null, Locale.ENGLISH)
            );
        });

            int confirmationCode = new Random().nextInt(900000) + 100000;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Hello " + user.getFirstName() + ",\n" + "Your confirmation code is " + confirmationCode + ".\n Have a nice day!");
            message.setTo(user.getEmail());
            message.setSubject("Confirm your email");
            message.setFrom("md8860276@gmail.com");
            mailSender.send(message);
            temporaryUsers.addUser(confirmationCode, user);
        

    }

    @Override
    public UserDTO createUser(int confirmationCode) {
        UserDTO user = temporaryUsers.getUser(confirmationCode).orElseThrow(() -> {
            throw new ResourceNotFoundException(
                    messageSource.getMessage("api.error.invalid.confirmation.code", null, Locale.ENGLISH)
            );
        });
        temporaryUsers.removeUser(confirmationCode);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDO userDO = userRepository.save(modelMapper.map(user, UserDO.class));
        return modelMapper.map(userDO, UserDTO.class);
    }

    @Override
    public void deleteUser(int id) {
        UserDO userDO = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH)));
        userRepository.delete(userDO);
    }

    @Override
    public AuthenticationResponse logIn(AuthenticationRequest credentials) {
        UserDO userDO = userRepository.findByEmail(credentials.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("api.error.invalid.email.or.passowrd", null, Locale.ENGLISH)));
        if (!passwordEncoder.matches(credentials.getPassword(), userDO.getPassword())) {
            new ResourceNotFoundException(messageSource.getMessage("api.error.invalid.email.or.passowrd", null, Locale.ENGLISH));
        }
        UserDTO userDTO = modelMapper.map(userDO, UserDTO.class);
        return new AuthenticationResponse(jwtGenerator.generate(userDTO));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setJwtGenerator(JWTGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @Autowired
    public void setTemporaryUsers(TemporaryUsers temporaryUsers) {
        this.temporaryUsers = temporaryUsers;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
