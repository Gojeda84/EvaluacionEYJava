package com.evaluacion.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.evaluacion.java.model.Usuario;
import com.evaluacion.java.repository.UserRepository;
import com.evaluacion.java.security.JwtTokenProvider;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    
    /**
     * Implementa la lógica de validación de correo aquí.
     * Se debe usar expresiones regulares u otras validaciones.
     * 
     * @param correo
     * @return
     */
    public boolean esCorreoValido(String correo) {
    	
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
    }

    
    /**
     * Implementa la lógica de validación de contraseña aquí.
     * Por ejemplo, asegúrate de que la contraseña tenga una mayúscula, minúsculas y números
     * 
     * @param contrasena
     * @return
     */
    public boolean esContrasenaValida(String contrasena) {
    	
        return contrasena.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2,}).{8,}$");
    }

    /**
     * Implementa la lógica para verificar si el correo ya existe en la base de datos
     * 
     * @param correo
     * @return
     */
    public boolean existeUsuarioPorCorreo(String correo) {
		return false;
    	
        //return userRepository.existsByEmail(correo);
    }
    /**
     * Realiza el registro del usuario en la base de datos y genera un token.
     * 
     * @param user
     * @return
     */
    public Usuario registrarUsuario(Usuario user) {
    	// Encriptar la contraseña antes de guardarla en la base de datos
        String contrasenaEncriptada = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(contrasenaEncriptada);
        
        // Guardar el usuario en la base de datos
        Usuario nuevoUsuario = userRepository.save(user);
        
        // Generar un token JWT y asignarlo al usuario
        String token = jwtTokenProvider.createToken(user.getEmail());
        nuevoUsuario.setToken(token);
        
        
        return nuevoUsuario;
    }

}
