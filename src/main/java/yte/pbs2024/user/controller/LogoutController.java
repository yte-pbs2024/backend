package yte.pbs2024.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yte.pbs2024.common.response.MessageResponse;
import yte.pbs2024.common.response.MessageType;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(0);
            response.addCookie(cookie);

            MessageResponse messageResponse = new MessageResponse("Başarılı bir şekilde çıkış yapıldı.", MessageType.SUCCESS);
            return ResponseEntity.ok(messageResponse);
        } catch (Exception e) {
            MessageResponse messageResponse = new MessageResponse("Logout işlemi sırasında bir hata oluştu: " + e.getMessage(), MessageType.ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageResponse);
        }
    }
}
