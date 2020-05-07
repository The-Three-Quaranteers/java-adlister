package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Message;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateProfileServlet", urlPatterns = "/update_user")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/updateUserInfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessUser = (User) request.getSession().getAttribute("user");
        long id = sessUser.getId();
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
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("email",email);
            response.sendRedirect("/update_user");
            return;
        }else
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
        User user = new User(id, username, email, password);
        DaoFactory.getUsersDao().updateUserInfo(user);
        errStr = "";
        errMsg.setDescription(errStr);
        request.getSession().setAttribute("message",errMsg);
        response.sendRedirect("/profile");
    }
}
