package org.example.service;

// Этот код представляет собой реализацию сервиса в Java Spring, который интегрируется с системой безопасности
// Spring Security. Сервис отвечает за загрузку данных пользователя по его имени (в данном случае — фамилии)
// и преобразование этих данных в объект UserDetails, необходимый для аутентификации пользователя в приложении.

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired // автовнедрение объекта UserRepository в этот класс.
    private UserRepository userRepository; // UserRepository используется для поиска пользователя в базе данных

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ищем пользователя в базе данных по фамилии (username).
        User user = userRepository.findBySurname(username);
        if (user == null) {  // пользователь не найден
            throw new UsernameNotFoundException("User not found");
        }
        // Если пользователь найден, создается объект UserDetails, содержащий данные, необходимые для аутентификации.
        return new org.springframework.security.core.userdetails.User(
                user.getSurname(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
