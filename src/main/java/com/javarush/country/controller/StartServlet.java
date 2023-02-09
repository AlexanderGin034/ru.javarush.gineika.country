package com.javarush.country.controller;

import com.javarush.country.service.providerService.PropertiesSessionFactoryProviderImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/StartServlet")
public class StartServlet extends HttpServlet {
    private SessionFactory sessionFactory;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String r = "";
        this.sessionFactory = new PropertiesSessionFactoryProviderImpl().getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String city = "Gent";
            String sql = "SELECT name FROM world.city where name = ?";
            Query<String> query = session.createNamedQuery(sql, String.class);
            query.setParameter(1, city);
            r = query.uniqueResult();
        }
        request.setAttribute("city", r);
        System.out.println("before forward");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
      //  this.sessionFactory = new PropertiesSessionFactoryProviderImpl().getSessionFactory();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
