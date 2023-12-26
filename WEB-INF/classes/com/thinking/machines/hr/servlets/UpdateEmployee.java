package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
public class UpdateEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("text/plain");
String employeeId=request.getParameter("employeeId");
String name=request.getParameter("name").trim();
int designationCode=Integer.parseInt(request.getParameter("designationCode"));
String date=request.getParameter("date");
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
Date d=sdf.parse(date);
String gender=request.getParameter("gender").trim();
String tmp=request.getParameter("indian");
boolean isIndian;
if(tmp==null)
{
isIndian=false;
}
else isIndian=true;
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary").trim());
String panNumber=request.getParameter("panNumber").trim();
String aadharCardNumber=request.getParameter("aadharCardNumber").trim();
EmployeeDAO employee=new EmployeeDAO();
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
EmployeeDTO e;
try
{
e=employee.getByPANNumber(panNumber);
if(e.getId().equals(employeeId)!=true) panNumberExists=true;
}catch(DAOException daoException)
{
}
try
{
e=employee.getByAadharCardNumber(aadharCardNumber);
if(e.getId().equals(employeeId)!=true) aadharCardNumberExists=true;
}catch(DAOException daoException)
{
}
if(panNumberExists==true)
{
pw.print("PAN number exists");
}
if(aadharCardNumberExists==true)
{
pw.print(",Aadhar card number exists");
}
if(panNumberExists || aadharCardNumberExists) return;
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setGender(gender);
employeeDTO.setDateOfBirth(d);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employee.updateEmployee(employeeDTO);
pw.print("Employee Updated Successfully");
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
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception exception)
{
//do nothing
}
}
}