/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproj1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Paweł
 */
public class JavaProj1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaProj1PU");
        EntityManager em = emf.createEntityManager();
       
        Student student = new Student("Wojtek" , new Date());
       
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
       
        System.out.println("1: "+em.find(Student.class, 7)); // no transaction for reading
        System.out.println("2: "+em.createQuery("SELECT s FROM Student s WHERE s.id=5")
                .getResultList()); //getSingleResult()
        System.out.println("3: "+em.createNamedQuery("Student.findAll")
                .getResultList());
        System.out.println("4: "+em.createNamedQuery("Student.findByName")
                .setParameter("name", "Wojtek%").setMaxResults(2).getResultList());
        System.out.println("5: "+em.createNamedQuery("Student.findByName")
                .setParameter("xyz", "Wojtek").setMaxResults(2).getResultList());
        System.out.println("6: "+em.createQuery(createCriteriaQuery(em, null, StudentType.PARTTIME))
                .getResultList());
        Student stud = em.find(Student.class, 3);
        stud.setName("Adam");
        
        em.getTransaction().begin();
        em.merge(stud);
        em.getTransaction().commit();
        em.close();
    }
    
    private static CriteriaQuery<Student> createCriteriaQuery(EntityManager em, String name, StudentType type) {
        Expression expr;
        Root<Student> queryRoot;
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Student> queryDefinition = builder.createQuery(Student.class);
        List<Predicate> predicates = new ArrayList<>();
        
        queryRoot = queryDefinition.from(Student.class);
        queryDefinition.select(queryRoot);
        
        if(name != null) {
            expr = queryRoot.get("name");
            predicates.add(builder.like(expr, name + "%"));
        }
        
        if(name != null) {
            expr = queryRoot.get("studentType");
            predicates.add(builder.equal(expr, type));
        }
        
        if(name != null) {
            queryDefinition.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
        }        
        return queryDefinition;
    }
}
