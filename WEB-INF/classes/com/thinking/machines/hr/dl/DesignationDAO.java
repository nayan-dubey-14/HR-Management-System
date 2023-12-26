package com.thinking.machines.hr.dl;
import java.util.*;
import java.sql.*;
public class DesignationDAO 
{
public DesignationDTO getByCode(int code) throws DAOException
{
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select * from designation where code=?");
ps.setInt(1,code);
ResultSet resultSet=ps.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
ps.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
DesignationDTO designation=new DesignationDTO();
designation.setCode(resultSet.getInt("code"));
designation.setTitle(resultSet.getString("title").trim());
resultSet.close();
ps.close();
connection.close();
return designation;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void deleteByCode(int code) throws DAOException
{
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select * from designation where code=?");
ps.setInt(1,code);
ResultSet rs=ps.executeQuery();
if(rs.next()==false)
{
rs.close();
ps.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
rs.close();
ps.close();
ps=connection.prepareStatement("select employee.gender from employee where designation_code=?");
ps.setInt(1,code);
rs=ps.executeQuery();
if(rs.next())
{
rs.close();
ps.close();
connection.close();
throw new DAOException("Unable to delete designation because it is already alloted to a employee");
}
rs.close();
ps.close();
ps=connection.prepareStatement("delete from designation where code=?");
ps.setInt(1,code);
ps.executeUpdate();
ps.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void updateDesignation(DesignationDTO designation) throws DAOException
{
try
{
int code=designation.getCode();
String title=designation.getTitle();
Connection connection=DAOConnector.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from designation where title=? and code!=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Title exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{ 
throw new DAOException(sqlException.getMessage());
}
}
public void addDesignation(DesignationDTO designation) throws DAOException
{
try
{
String title=designation.getTitle();
Connection connection=DAOConnector.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where title=?");
preparedStatement.setString(1,title);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation "+title+" exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into designation(title) value(?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,title);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int code=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designation.setCode(code);
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage()); //remove after testing
}
}
public List<DesignationDTO> getAll() throws DAOException
{
try
{
List<DesignationDTO> designations=new LinkedList<>();
DesignationDTO designationDTO;
int code;
String title;
Connection connection=DAOConnector.getConnection();
PreparedStatement ps=connection.prepareStatement("select * from designation");
ResultSet rs=ps.executeQuery();
while(rs.next())
{
code=rs.getInt("code");
title=rs.getString("title");
designationDTO=new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);
}
rs.close();
ps.close();
connection.close();
return designations;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
public boolean designationExists(int code) throws DAOException
{
boolean exists=false;
try
{
Connection connection=DAOConnector.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select code from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
exists=resultSet.next();
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return exists;
}
}