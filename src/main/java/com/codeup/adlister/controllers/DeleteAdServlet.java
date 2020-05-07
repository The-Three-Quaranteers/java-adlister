package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete")
public class DeleteAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        long ad_id = Long.parseLong(request.getParameter("ad_id"));
        Ad adToDelete = DaoFactory.getAdsDao().getAdByID(ad_id);
        DaoFactory.getAdsDao().deleteAd(adToDelete);
        response.sendRedirect("/profile");
//        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
