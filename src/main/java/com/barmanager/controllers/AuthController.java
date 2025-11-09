package com.barmanager.controllers;

import com.barmanager.models.*;
import com.barmanager.repositories.*;
import com.barmanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private BartenderRepository bartenderRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    /**
     * POST /api/auth/login
     * Autenticación de usuarios
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usuario = credentials.get("usuario");
        String contrasena = credentials.get("contrasena");
        
        // Buscar en bartenders
        Optional<Bartender> bartender = bartenderRepository.findByUsuario(usuario);
        if (bartender.isPresent() && 
            passwordEncoder.matches(contrasena, bartender.get().getContrasena())) {
            
            String token = jwtUtil.generateToken(usuario, "BARTENDER");
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", bartender.get());
            response.put("rol", "BARTENDER");
            
            return ResponseEntity.ok(response);
        }
        
        // Buscar en administradores
        Optional<Administrador> admin = administradorRepository.findByUsuario(usuario);
        if (admin.isPresent() && 
            passwordEncoder.matches(contrasena, admin.get().getContrasena())) {
            
            String token = jwtUtil.generateToken(usuario, "ADMINISTRADOR");
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("usuario", admin.get());
            response.put("rol", "ADMINISTRADOR");
            
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(401)
            .body(Map.of("error", "Credenciales inválidas"));
    }
    
    /**
     * POST /api/auth/register/bartender
     * Registro de nuevo bartender
     */
    @PostMapping("/register/bartender")
    public ResponseEntity<?> registrarBartender(@RequestBody Bartender bartender) {
        if (bartenderRepository.findByUsuario(bartender.getUsuario()).isPresent()) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Usuario ya existe"));
        }
        
        bartender.setContrasena(passwordEncoder.encode(bartender.getContrasena()));
        Bartender saved = bartenderRepository.save(bartender);
        
        return ResponseEntity.ok(Map.of(
            "mensaje", "Bartender registrado exitosamente",
            "usuario", saved
        ));
    }
    
    /**
     * POST /api/auth/register/admin
     * Registro de nuevo administrador (requiere autenticación de admin existente)
     */
    @PostMapping("/register/admin")
    public ResponseEntity<?> registrarAdmin(@RequestBody Administrador admin) {
        if (administradorRepository.findByUsuario(admin.getUsuario()).isPresent()) {
            return ResponseEntity.badRequest()
                .body(Map.of("error", "Usuario ya existe"));
        }
        
        admin.setContrasena(passwordEncoder.encode(admin.getContrasena()));
        Administrador saved = administradorRepository.save(admin);
        
        return ResponseEntity.ok(Map.of(
            "mensaje", "Administrador registrado exitosamente",
            "usuario", saved
        ));
    }
}