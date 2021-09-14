package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args){
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try{
             int studentId = 1;
            //start a transaction
            session.beginTransaction();


            System.out.println("\nGetting student with id: "+studentId);
            Student myStudent = session.get(Student.class, studentId);

            //delete myStudent
//            System.out.println("Deleting student: " + myStudent);
//            session.delete(myStudent);

            //delete student id=2
            System.out.println("Deleting student with id = 2");
            session.createQuery("delete from Student where id = 2").executeUpdate();
            session.getTransaction().commit();

            //New Code


            System.out.println("Done!");
        }
        finally {
            factory.close();
        }
    }
}
