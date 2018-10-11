/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproj1;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author wojte
 */
@Entity
@Table(name = "my_grade")
public class Grade implements Serializable {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="mark")
    private Double mark;
    
    @ManyToOne(optional = false)
    private Student student;
    
    private double getMark() {
        return mark;
    }
    
    public void setMark(Double mark){
        this.mark = mark;
    }
    
        public void setStudent(Student student){
        this.student = student;
    }

    
    
}
