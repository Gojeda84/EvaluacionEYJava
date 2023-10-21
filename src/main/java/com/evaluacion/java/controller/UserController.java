package com.evaluacion.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.java.model.User;
import com.evaluacion.java.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody User user) {
        // Aquí puedes realizar la validación de correo y contraseña
        if (!userService.esCorreoValido(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El formato de correo no es válido.");
        }

        if (!userService.esContrasenaValida(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El formato de contraseña no es válido.");
        }

        // Verificar si el correo ya existe en la base de datos
        if (userService.existeUsuarioPorCorreo(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está registrado.");
        }

        // Si pasa todas las validaciones, registra el usuario
        User nuevoUsuario = userService.registrarUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

}
