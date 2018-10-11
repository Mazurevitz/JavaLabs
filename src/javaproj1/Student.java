/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproj1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Paweł
 */
@Entity
@Table(name="Student")
@NamedQueries({@NamedQuery(name="Student.findAll",query="SELECT s FROM Student s"),
    @NamedQuery(name="Student.findByName",query="SELECT s FROM Student s WHERE s.name LIKE :name ORDER BY s.studentType")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Temporal(TemporalType.DATE)
    @Column(name="birthdata")
    private Date birthdate;
    
    @Enumerated(EnumType.STRING)
    @Column(name="studenttype")
    private StudentType studentType;


    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public Student(){
        
    }

    public Student(String name, Date date){
        this.birthdate = date;
        this.name = name;
        this.studentType = StudentType.FULLTIME;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString() {
        return new StringBuilder().append(id).append(" ,")
                .append(name).append(" , ")
                .append(new SimpleDateFormat("dd/MM/yyyy").format(birthdate))
                .append(", ").append(studentType).toString();
           }    
}
