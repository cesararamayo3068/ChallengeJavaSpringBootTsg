package com.developers.user.controllers;

import com.developers.user.entities.User;
import com.developers.user.services.UserService;
import com.developers.user.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    public List<User> getUsers(@RequestHeader("Authorization") String token) {
        if (!isAuthenticated(token)) {
            throw new RuntimeException("No autenticado");
        }
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (!isAuthenticated(token)) {
            throw new RuntimeException("No autenticado");
        }
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user, @RequestHeader("Authorization") String token) {
        if (!isAuthenticated(token)) {
            throw new RuntimeException("No autenticado");
        }

        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            throw new RuntimeException("Usuario no encontrado");
        }


        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            // Hashear la nueva contraseña
            String hashedPassword = hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        } else {
            user.setPassword(existingUser.getPassword());
        }

        userService.updateUser(id, user);
        return "Usuario actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (!isAuthenticated(token)) {
            throw new RuntimeException("No autenticado");
        }
        userService.deleteUser(id);
        return "Usuario eliminado correctamente";
    }


    private boolean isAuthenticated(String token) {
        try {
            if (token != null && token.startsWith("Bearer ")) {

                token = token.substring(7);

                return jwtUtils.validateToken(token);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al validar el token JWT: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

}
