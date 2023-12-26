package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class AddDesignation extends HttpServlet
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
DesignationDTO designation=gson.fromJson(jsonString,DesignationDTO.class);
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
DesignationDTO designationDTO=new DesignationDTO();
designationDTO.setTitle(designation.getTitle());
DesignationDAO designationDAO=new DesignationDAO();
try
{
designationDAO.addDesignation(designationDTO);
jsonString=gson.toJson("Designation Added Successfully");
}catch(DAOException daoException)
{
//if invalid
jsonString=gson.toJson(daoException.getMessage());
}
pw.print(jsonString);
pw.flush();
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