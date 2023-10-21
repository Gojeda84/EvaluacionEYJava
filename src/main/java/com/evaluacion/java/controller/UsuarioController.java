package com.evaluacion.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.java.model.Usuario;
import com.evaluacion.java.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

	@Autowired
	private UserService userService;

	@PostMapping("/registro")
	public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
		
		// Valida si el correo es valido.
		if (!userService.esCorreoValido(usuario.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El formato de correo no es válido.");
		}

		// Valida si la contraseña es valida.
		if (!userService.esContrasenaValida(usuario.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El formato de contraseña no es válido.");
		}

		// Verifica si el correo existe.
		if (userService.existeUsuarioPorCorreo(usuario.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está registrado.");
		}

		// Si pasa todas las validaciones, registra el usuario.
		Usuario nuevoUsuario = userService.registrarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
	}

}
