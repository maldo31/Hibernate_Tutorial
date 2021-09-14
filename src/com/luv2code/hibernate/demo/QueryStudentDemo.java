package com.luv2code.hibernate.demo;


import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

    public static void main(String[] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{


            //start a transaction
            session.beginTransaction();

            List<Student> theStudents = session.createQuery("from Student").getResultList();

            displayStudents(theStudents);

            //query student with last name Doe

            theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
            System.out.println("Student with last name of Doe");
            displayStudents(theStudents);

            //query student with last name Doe or firstname Daffy

            theStudents = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
            System.out.println("Student with last name of Doe or first name Duffy");
            displayStudents(theStudents);


            //query student with email LIKE '%luv2code.com

            theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
            System.out.println("Student with email ending like luv2code.com");
            displayStudents(theStudents);




            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student theStudent : theStudents){
            System.out.println(theStudent);
        }
    }
}
