package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Message;
import com.codeup.adlister.models.User;
import com.mysql.cj.api.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        Message errMsg = new Message();
        String errStr = "";
        errMsg.setDescription("");

        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation));

        if (inputHasErrors) {
            if (username.isEmpty()){
                errStr+= "* Username Required! ";
            }
            if (email.isEmpty()){
                errStr+= "* Email Required! ";
            }
            if (password.isEmpty()){
                errStr+= "* Password Required! ";
            }
            if (! password.equals(passwordConfirmation) || passwordConfirmation.equals("")){
                errStr+= "* Passwords must match! ";
            }

            errMsg.setDescription(errStr);

            request.getSession().setAttribute("message",errMsg);

            response.sendRedirect("/register");
            return;
        } else
        if (DaoFactory.getUsersDao().findByUsername(username) != null) {
            errMsg.setDescription("* Username already used! *");
            request.getSession().setAttribute("message",errMsg);

            response.sendRedirect("/register");
            return;
        } else
        if (DaoFactory.getUsersDao().findByEmail(email) != null) {
            errMsg.setDescription("* Email already used! *");
            request.getSession().setAttribute("message",errMsg);

            response.sendRedirect("/register");
            return;
        }

        //create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
