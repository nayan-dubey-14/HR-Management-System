package com.thinking.machines.hr.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
public class Logout extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception exception)
{
//do nothing
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
request.getSession().removeAttribute("username");
RequestDispatcher requestDispatcher=request.getRequestDispatcher("LoginForm.jsp");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{
//do nothing
}
}
}
}