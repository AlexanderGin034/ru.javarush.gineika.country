package com.javarush.country.controller;

import com.javarush.country.service.StartTest;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/speedTest")
public class SpeedTestServlet extends HttpServlet {
    private StartTest startTest;
    @Override
    public void init() throws ServletException {
        super.init();
        startTest = new StartTest();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("redis", startTest.getRedisTime());
        request.setAttribute("mysql", startTest.geMysqlTime());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
