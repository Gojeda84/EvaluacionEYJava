package com.evaluacion.java.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
	private String secretKey = "TuClaveSecreta"; // Cambia esto por una clave segura

	/**
	 * Implementa la creación del token JWT aquí
	 * 
	 * @param correo
	 * @return
	 */
	public String createToken(String correo) {
		return correo;
	}

	/**
	 * Implementa la validación del token JWT aquí
	 * 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		return false;
	}

}
