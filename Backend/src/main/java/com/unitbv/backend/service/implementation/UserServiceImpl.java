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
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private MessageSource messageSource;
    private JWTGenerator jwtGenerator;
    private JavaMailSender mailSender;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAll() {

        List<UserDO> users = userRepository.findAllOrderByIdASC();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        userRepository.findByEmail(user.getEmail()).ifPresent((u) -> {
            throw new ResourceAlreadyExistsException(
                    messageSource.getMessage("api.error.user.already.exists", null, Locale.ENGLISH)
            );
        });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserDO userDO = userRepository.save(modelMapper.map(user, UserDO.class));
        return modelMapper.map(userDO, UserDTO.class);
    }

    @Override
    public UserDTO editUser(UserDTO user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException(messageSource.getMessage("api.error.user.not.found", null, Locale.ENGLISH));
                });
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
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
