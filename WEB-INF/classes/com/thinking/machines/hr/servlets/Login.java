package com.thinking.machines.hr.servlets;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class Login extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
//do nothing
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
BufferedReader br=request.getReader();
String s;
StringBuffer sb=new StringBuffer();
while(true)
{
s=br.readLine();
if(s==null) break;
sb.append(s);
}
String jsonString=sb.toString();
Gson gson=new Gson();
AdministratorDTO admin=gson.fromJson(jsonString,AdministratorDTO.class);
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
try
{
AdministratorDAO administrator=new AdministratorDAO();
AdministratorDTO administratorDTO=administrator.getByUsername(admin.getUsername());
if(administratorDTO.getPassword().equals(admin.getPassword())==false)
{
jsonString=gson.toJson("Invalid username/password");
pw.print(jsonString);
pw.flush();
return;
}
request.getSession().setAttribute("username",administratorDTO.getUsername());
jsonString=gson.toJson("Succesfully Login");
pw.print(jsonString);
pw.flush();
}catch(DAOException daoException)
{
jsonString=gson.toJson("Invalid username/password");
pw.print(jsonString);
pw.flush();
return;
}
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