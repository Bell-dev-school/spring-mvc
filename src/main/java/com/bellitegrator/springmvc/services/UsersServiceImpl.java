package com.bellitegrator.springmvc.services;

import com.bellitegrator.springmvc.exceptions.UserNotFoundException;
import com.bellitegrator.springmvc.forms.UserForm;
import com.bellitegrator.springmvc.models.User;
import com.bellitegrator.springmvc.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger("UsersServiceImpl");

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getUser(Integer userId) {
        LOGGER.info("Пытаемся получить пользователя по его id = " + userId);
        return usersRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Получаем список всех пользователей");
        return usersRepository.findAll();
    }

    @Override
    public void updateUser(User user, UserForm userForm) {
        LOGGER.info("Получили пользователя " + user.getId() + " и данные для его обновления");
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setTelNumber(userForm.getTelNumber());
        user.setEmail(userForm.getEmail());
        user.setHashPassword(passwordEncoder.encode(userForm.getPassword()));
        LOGGER.info("Обновили пользователя " + user.getId());
        usersRepository.save(user);
        LOGGER.info("Сохранили пользователя " + user.getId() + " в репозиторий");
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info("Пытаемся получить пользователя по email: " + email);
        return usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найлен"));
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authUser = SecurityContextHolder.getContext().getAuthentication();
        String emailAuthUser = authUser.getName();
        LOGGER.info("Получаем обьект залогиненного пользователя c email: " + emailAuthUser);
        return usersRepository.findByEmail(emailAuthUser).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найлен"));
    }



}
