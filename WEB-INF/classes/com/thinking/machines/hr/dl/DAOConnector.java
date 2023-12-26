package com.thinking.machines.hr.dl;
import java.sql.*;
import java.io.*;
public class DAOConnector 
{
private DAOConnector(){}
public static Connection getConnection() throws DAOException
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmdbuser","tmdbuser");
return con;
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
}
}
