package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class AddEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
BufferedReader br=request.getReader();
String str;
StringBuffer sb=new StringBuffer();
while(true)
{
str=br.readLine();
if(str==null) break;
sb.append(str);
}
String jsonString=sb.toString();
Gson gson=new Gson();
EmployeeDTO emp=gson.fromJson(jsonString,EmployeeDTO.class);
PrintWriter pw=response.getWriter();
response.setContentType("applicatin/json");
response.setCharacterEncoding("utf-8");
EmployeeDAO employee=new EmployeeDAO();
boolean panNumberExists=employee.panNumberExists(emp.getPANNumber());
boolean aadharCardNumberExists=employee.aadharCardNumberExists(emp.getAadharCardNumber());
if(panNumberExists==true && aadharCardNumberExists==true)
{
jsonString=gson.toJson("PAN and Aadhar Card number exists");
pw.print(jsonString);
pw.flush();
return;
}
if(panNumberExists==true)
{
jsonString=gson.toJson("PAN number exists");
pw.print(jsonString);
pw.flush();
return;
}
if(aadharCardNumberExists==true)
{
jsonString=gson.toJson("Aadhar Card Number exists");
pw.print(jsonString);
pw.flush();
return;
}
EmployeeDTO employeeDTO=new EmployeeDTO();
employeeDTO.setName(emp.getName());
employeeDTO.setDesignationCode(emp.getDesignationCode());
employeeDTO.setGender(emp.getGender());
employeeDTO.setDateOfBirth(emp.getDateOfBirth());
employeeDTO.setBasicSalary(emp.getBasicSalary());
employeeDTO.setIsIndian(emp.getIsIndian());
employeeDTO.setPANNumber(emp.getPANNumber());
employeeDTO.setAadharCardNumber(emp.getAadharCardNumber());
employee.addEmployee(employeeDTO);
jsonString=gson.toJson("Employee Added Successfully");
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