package org.example.mytarocard.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mytarocard.service.SupabaseService;
import org.example.mytarocard.service.SupabaseServiceImpl;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/result/*")
public class TaroResultController extends Controller {
    SupabaseService supabaseService;
    @Override
    public void init() throws ServletException {
        log("TaroResultController Init");
        supabaseService = SupabaseServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log("TaroResultController doGet");
        String uuid = req.getPathInfo().substring(1); // /{uuid} -> / 없애주기 위해서 앞에 1개 없앰
//        req.setAttribute("uuid", uuid);
        try {
            req.setAttribute("data", supabaseService.findById(uuid));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        view(req, resp, "result");
    }

}
