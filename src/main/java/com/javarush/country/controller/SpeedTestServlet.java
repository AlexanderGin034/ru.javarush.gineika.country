package com.javarush.country.controller;

import com.javarush.country.service.StartService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/speedTest")
public class SpeedTestServlet extends HttpServlet {
    private StartService startService;
    @Override
    public void init() throws ServletException {
        super.init();
        startService = new StartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("redis", startService.getRedisTime());
        request.setAttribute("mysql", startService.geMysqlTime());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
