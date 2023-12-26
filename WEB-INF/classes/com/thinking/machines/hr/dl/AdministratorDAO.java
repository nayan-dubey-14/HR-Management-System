package com.thinking.machines.hr.dl;
import java.sql.*;
public class AdministratorDAO
{
public AdministratorDTO getByUsername(String username) throws DAOException
{
try
{
if(username==null) 
{
throw new DAOException("Invalid username");
}
Connection connection=DAOConnector.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from administrator where uname=?");
preparedStatement.setString(1,username);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid username");
}
String userName=resultSet.getString("uname");
if(userName.equals(username)==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid username");
}
AdministratorDTO administratorDTO=new AdministratorDTO();
administratorDTO.setUsername(userName);
administratorDTO.setPassword(resultSet.getString("pwd"));
resultSet.close();
preparedStatement.close();
connection.close();
return administratorDTO;
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
}