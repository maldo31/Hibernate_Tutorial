package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{

            //use the session object to save Java object
            System.out.println("Creating new student object");
            //create a student object
            Student tempStudent = new Student("Daffy","Duck","paul@luv2code.com");
            //start a transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);
            //commit transaction
            session.getTransaction().commit();

            //NEW CODE

            //find out what the student id:primary key
            System.out.println("Saved student. Generated id: " + tempStudent.getId());

            //nwo get a new session and transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            //retrive student from DB by id
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class,tempStudent.getId());

            System.out.println("Get complete: " + myStudent);
            //commit the transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
