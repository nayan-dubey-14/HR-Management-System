package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class GetByEmployeeId extends HttpServlet
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
PrintWriter pw=response.getWriter();
response.setContentType("text/plain");
String employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
EmployeeDTO employee=employeeDAO.getByEmployeeId(employeeId);
pw.print(employee.getId()+","+employee.getName()+","+employee.getDesignationCode()+","+employee.getTitle()+","+employee.getGender()+","+employee.getDateOfBirth()+","+employee.getBasicSalary()+","+employee.getIsIndian()+","+employee.getPANNumber()+","+employee.getAadharCardNumber());
}catch(DAOException daoException)
{
pw.print("INVALID");
}
}catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
//do nothing
}
}
}
}