package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.forms.SignUpForm;
import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SignUpServiceImpl implements SignUpService {

    private static final Logger LOGGER = LoggerFactory.getLogger("SignUpServiceImpl");

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Override
    public void signUpUser(SignUpForm form) {
        LOGGER.info("Получили данные о пользователе");
        User user = User.builder()
                .name(form.getName())
                .surname(form.getSurname())
                .telNumber(form.getTelNumber())
                .email(form.getEmail())
                .role(form.getRole())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();
        usersRepository.save(user);
        LOGGER.info("Сохранили пользователя в репозиторий");
    }

    @Override
    public boolean isUserExist(String email) {
        LOGGER.info("Передаем boolean, существует уже пользователь с таким email или нет");
        return usersRepository.findByEmail(email).isPresent();
    }
}

