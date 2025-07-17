package com.vvdn.nms.nms_backend.config.security;

import com.vvdn.nms.nms_backend.repository.UserRepository;
import com.vvdn.nms.nms_backend.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userRepo = userRepo;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        // ✅ Skip filter for public endpoints
        if (path.contains("/login") || path.contains("/signup")) {
            chain.doFilter(request, response);
            return;
        }

        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = jwtUtil.extractUsername(token);

            var userOpt = userRepo.findByEmail(email);
            if (userOpt.isPresent()) {
                var user = userOpt.get();

                Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

                var auth = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

}
