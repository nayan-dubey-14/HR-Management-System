package com.thinking.machines.hr.dl;
import java.util.*;
import java.sql.*;
import java.lang.*;
import java.text.*;
import java.io.*;
public class EmployeeDAO 
{
public List<EmployeeDTO> getAll() throws DAOException
{
try
{
List<EmployeeDTO> employees=new LinkedList<>();
EmployeeDTO employeeDTO;
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.gender,employee.date_of_birth,employee.basic_salary,employee.is_indian,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.designation_code=designation.code");
ResultSet resultSet=ps.executeQuery();
while(resultSet.next())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setId("A"+resultSet.getInt("id"));
employeeDTO.setName(resultSet.getString("name").trim());
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
employeeDTO.setTitle(resultSet.getString("title").trim());
employeeDTO.setGender(resultSet.getString("gender").trim());
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employees.add(employeeDTO);
}
resultSet.close();
ps.close();
connection.close();
return employees;
}catch(Exception e)
{
throw new DAOException(e.getMessage()+" problem");
}
}
public void addEmployee(EmployeeDTO employeeDTO) throws DAOException
{
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select * from employee where employee.pan_number=?");
ps.setString(1,employeeDTO.getPANNumber());
ResultSet rs=ps.executeQuery();
if(rs.next())
{
rs.close();
ps.close();
connection.close();
throw new DAOException("PAN Number: "+employeeDTO.getPANNumber()+" exists");
}
rs.close();
ps.close();
ps=connection.prepareStatement("select * from employee where aadhar_card_number=?");
ps.setString(1,employeeDTO.getAadharCardNumber());
rs=ps.executeQuery();
if(rs.next())
{
rs.close();
ps.close();
connection.close();
throw new DAOException("Aadhar Card Number: "+employeeDTO.getAadharCardNumber()+" exists");
}
rs.close();
ps.close();
ps=connection.prepareStatement("insert into employee(name,designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
ps.setString(1,employeeDTO.getName());
ps.setInt(2,employeeDTO.getDesignationCode());
java.util.Date utilDate=employeeDTO.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
ps.setDate(3,sqlDate);
ps.setString(4,employeeDTO.getGender());
ps.setBoolean(5,employeeDTO.getIsIndian());
ps.setBigDecimal(6,employeeDTO.getBasicSalary());
ps.setString(7,employeeDTO.getPANNumber());
ps.setString(8,employeeDTO.getAadharCardNumber());
ps.executeUpdate();
rs=ps.getGeneratedKeys();
rs.next();
employeeDTO.setId("A"+rs.getInt(1));
rs.close();
ps.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public void updateEmployee(EmployeeDTO employeeDTO) throws DAOException
{
try
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeDTO.getId().substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid Employee Id : "+employeeDTO.getId());
}
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select gender from employee where employee.id=?");
ps.setInt(1,actualEmployeeId);
ResultSet rs=ps.executeQuery();
if(rs.next()==false)
{
rs.close();
ps.close();
connection.close();
throw new DAOException("Employee Id : "+employeeDTO.getId()+" did not exists");
}
rs.close();
ps.close();
ps=connection.prepareStatement("select * from employee where employee.pan_number=? and employee.id!=?");
ps.setString(1,employeeDTO.getPANNumber());
ps.setInt(2,actualEmployeeId);
rs=ps.executeQuery();
if(rs.next())
{
rs.close();
ps.close();
connection.close();
throw new DAOException("PAN Number: "+employeeDTO.getPANNumber()+" exists");
}
rs.close();
ps.close();
ps=connection.prepareStatement("select * from employee where aadhar_card_number=? and employee.id!=?");
ps.setString(1,employeeDTO.getAadharCardNumber());
ps.setInt(2,actualEmployeeId);
rs=ps.executeQuery();
if(rs.next())
{
rs.close();
ps.close();
connection.close();
throw new DAOException("Aadhar Card Number: "+employeeDTO.getAadharCardNumber()+" exists");
}
rs.close();
ps.close();
ps=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,gender=?,is_indian=?,basic_salary=?,pan_number=?,aadhar_card_number=? where employee.id=?");
ps.setString(1,employeeDTO.getName());
ps.setInt(2,employeeDTO.getDesignationCode());
java.util.Date utilDate=employeeDTO.getDateOfBirth();
java.sql.Date sqlDate=new java.sql.Date(utilDate.getYear(),utilDate.getMonth(),utilDate.getDate());
ps.setDate(3,sqlDate);
ps.setString(4,employeeDTO.getGender());
ps.setBoolean(5,employeeDTO.getIsIndian());
ps.setBigDecimal(6,employeeDTO.getBasicSalary());
ps.setString(7,employeeDTO.getPANNumber());
ps.setString(8,employeeDTO.getAadharCardNumber());
ps.setInt(9,actualEmployeeId);
ps.executeUpdate();
ps.close();
connection.close();
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean panNumberExists(String panNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.gender from employee where employee.pan_number=?");
ps.setString(1,panNumber);
ResultSet resultSet=ps.executeQuery();
exists=resultSet.next();
resultSet.close();
ps.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return exists;
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.gender from employee where employee.aadhar_card_number=?");
ps.setString(1,aadharCardNumber);
ResultSet resultSet=ps.executeQuery();
exists=resultSet.next();
resultSet.close();
ps.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return exists;
}
public boolean employeeIdExists(String employeeId) throws DAOException
{
boolean exists;
try
{
int actualEmployeeId=0;
try
{
actualEmployeeId=Integer.parseInt(employeeId.substring(1));
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.gender from employee where employee.id=?");
ps.setInt(1,actualEmployeeId);
ResultSet resultSet=ps.executeQuery();
exists=resultSet.next();
resultSet.close();
ps.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}

return exists;
}
public void deleteEmployee(String id) throws DAOException
{
int employeeId=0;
try
{
employeeId=Integer.parseInt(id.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid Employee Id : "+id);
}
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.gender from employee where employee.id=?");
ps.setInt(1,employeeId);
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
ps.close();
connection.close();
throw new DAOException("Employee Id : "+id+" did not exists");
}
resultSet.close();
ps.close();
ps=connection.prepareStatement("delete from employee where employee.id=?");
ps.setInt(1,employeeId);
ps.executeUpdate();
ps.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public EmployeeDTO getByEmployeeId(String id) throws DAOException
{
EmployeeDTO employeeDTO=new EmployeeDTO();
int employeeId=0;
try
{
employeeId=Integer.parseInt(id.substring(1));
}catch(Exception exception)
{
throw new DAOException("Invalid Employee Id : "+id);
}
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.gender,employee.date_of_birth,employee.basic_salary,employee.is_indian,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.id=? && employee.designation_code=designation.code");
ps.setInt(1,employeeId);
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
ps.close();
connection.close();
throw new DAOException("Invalid Employee Id : "+id);
}
employeeDTO.setId("A"+resultSet.getInt("id"));
employeeDTO.setName(resultSet.getString("name").trim());
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
employeeDTO.setTitle(resultSet.getString("title").trim());
employeeDTO.setGender(resultSet.getString("gender").trim());
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
resultSet.close();
ps.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return employeeDTO;
}
public EmployeeDTO getByPANNumber(String panNumber) throws DAOException
{
EmployeeDTO employeeDTO=new EmployeeDTO();
try
{

Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.gender,employee.date_of_birth,employee.basic_salary,employee.is_indian,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.pan_number=? && employee.designation_code=designation.code");
ps.setString(1,panNumber);
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
ps.close();
connection.close();
throw new DAOException("PAN Number : "+panNumber+" did not exists");
}
employeeDTO.setId("A"+resultSet.getInt("id"));
employeeDTO.setName(resultSet.getString("name").trim());
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
employeeDTO.setTitle(resultSet.getString("title").trim());
employeeDTO.setGender(resultSet.getString("gender").trim());
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
resultSet.close();
ps.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}

return employeeDTO;
}
public EmployeeDTO getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
EmployeeDTO employeeDTO=new EmployeeDTO();
try
{

Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.gender,employee.date_of_birth,employee.basic_salary,employee.is_indian,employee.pan_number,employee.aadhar_card_number from employee inner join designation on employee.aadhar_card_number=? && employee.designation_code=designation.code");
ps.setString(1,aadharCardNumber);
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
ps.close();
connection.close();
throw new DAOException("Aadhar Card Number : "+aadharCardNumber+" did not exists");
}
employeeDTO.setId("A"+resultSet.getInt("id"));
employeeDTO.setName(resultSet.getString("name").trim());
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));
employeeDTO.setTitle(resultSet.getString("title").trim());
employeeDTO.setGender(resultSet.getString("gender").trim());
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
resultSet.close();
ps.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}

return employeeDTO;
}
}