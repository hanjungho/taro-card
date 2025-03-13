package org.example.mytarocard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class Controller extends HttpServlet {
    // logger를 구현할 필요는 없음

    protected final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    protected final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화
    // HttpServlet -> 서버 -> logger

    protected void view(HttpServletRequest req, HttpServletResponse resp, String name) throws ServletException, IOException {
        String prefix = "/WEB-INF/views";
        String suffix = "jsp";
        // 접속하려는 주소는 유지한채로 특정한 경로로 이동
        req.getRequestDispatcher("%s/%s.%s".formatted(prefix, name, suffix)).forward(req, resp);
    }
}
