package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Message;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateAdServlet", urlPatterns = "/ads/update")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        if (request.getSession().getAttribute("ad") != null) {
            Ad adToEdit = (Ad) request.getSession().getAttribute("ad");
        }
        System.out.println(request);
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Message errMsg = new Message(); //instantiate the message obj.
        long ad_id = Long.parseLong(request.getParameter("ad_id"));
        String errStr = "";
        Ad adToEdit = DaoFactory.getAdsDao().getAdByID(ad_id);
        String title = adToEdit.getTitle();
        String description = adToEdit.getDescription();

        // validate input
        boolean inputHasErrors = title.isEmpty()
                || description.isEmpty();


        if (inputHasErrors) {
            if (title.isEmpty()){
                errStr+= "* Title Required! ";
            }
            if (description.isEmpty()){
                errStr+= "* Description Required! ";
            }

            errMsg.setDescription(errStr);

            request.getSession().setAttribute("message",errMsg);
            request.getSession().setAttribute("title",title);
            request.getSession().setAttribute("description",description);
            response.sendRedirect("/ads/update");
            return;
        }


//        User user = (User) request.getSession().getAttribute("user");
//        Ad ad = new Ad(
//                user.getId(),
//                request.getParameter("title"),
//                request.getParameter("description")
//        );
//        DaoFactory.getAdsDao().insert(ad);
//        response.sendRedirect("/ads");
    }
}

