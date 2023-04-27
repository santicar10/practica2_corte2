package com.example.practica2_corte2.service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface LoginService {
    Optional<String> getUsername(HttpServletRequest req);
}
