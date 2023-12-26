package com.thinking.machines.hr.dl;
import java.util.*;
import java.math.*;
public class EmployeeDTO implements java.io.Serializable,Comparable<EmployeeDTO>
{
private String id;
private String name;
private int designationCode;
private String title;
private String gender;
private Date dateOfBirth;
private BigDecimal basicSalary;
private boolean isIndian;
private String panNumber;
private String aadharCardNumber;
public EmployeeDTO()
{
this.id="";
this.name="";
this.designationCode=0;
this.title="";
this.gender="";
this.dateOfBirth=null;
this.basicSalary=null;
this.isIndian=false;
this.panNumber="";
this.aadharCardNumber="";
}
public void setId(java.lang.String id)
{
this.id=id;
}
public java.lang.String getId()
{
return this.id;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
public void setTitle(java.lang.String title)
{
this.title=title;
}
public java.lang.String getTitle()
{
return this.title;
}
public void setGender(java.lang.String gender)
{
this.gender=gender;
}
public java.lang.String getGender()
{
return this.gender;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setBasicSalary(java.math.BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public java.math.BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setPANNumber(java.lang.String panNumber)
{
this.panNumber=panNumber;
}
public java.lang.String getPANNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public boolean equals(Object other)
{
if(!(other instanceof EmployeeDTO)) return false;
EmployeeDTO emp=(EmployeeDTO)other;
return this.id.equalsIgnoreCase(emp.id); 
}
public int hashCode()
{
return this.id.hashCode();
}
public int compareTo(EmployeeDTO employeeDTO)
{
return this.id.compareToIgnoreCase(employeeDTO.id);
}
}