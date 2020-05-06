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

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Message errMsg = new Message(); //instantiate the message obj.
        String title = request.getParameter("title"); // get parameter for title from the post after submission
        String description = request.getParameter("description"); // get parameters for description from the post after submission
        String errStr = "";
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
            response.sendRedirect("/ads/create");
            return;
        }


        User user = (User) request.getSession().getAttribute("user");
        Ad ad = new Ad(
            user.getId(),
            request.getParameter("title"),
            request.getParameter("description")
        );
        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/ads");
    }
}
