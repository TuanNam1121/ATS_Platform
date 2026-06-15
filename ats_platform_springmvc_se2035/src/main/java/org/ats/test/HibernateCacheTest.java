package org.ats.test;

import org.ats.config.WebDataConfig;
import org.ats.entities.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateCacheTest {
    public static void main(String[] args) throws InterruptedException {
        // First cache
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebDataConfig.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        long start1 = System.nanoTime();
        Job job1 = session.get(Job.class, 1L);
        long end1 = System.nanoTime();
        System.out.println("ID job1: " + job1.getId());
        System.out.printf("DB Query: %.3f ms%n", (end1 - start1) / 1_000_000.0);

        long start2 = System.nanoTime();
        Job job2 = session.get(Job.class, 1L);
        long end2 = System.nanoTime();
        System.out.println("ID job2: " + job2.getId());
        System.out.printf("Cache Query: %.3f ms%n", (end2 - start2) / 1_000_000.0);

        Session newSession = sessionFactory.openSession();
        long start3 = System.nanoTime();
        Job job3 = newSession.get(Job.class, 1L);
        long end3 = System.nanoTime();
        System.out.printf("DB Query in newSession: %.3f ms%n", (end3 - start3) / 1_000_000.0);

        session.getTransaction().commit();


        // Second cache
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebDataConfig.class);
//        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
//        Session session1 = sessionFactory.openSession();
//        long t1 = System.nanoTime();
//        Job job1 = session1.get(Job.class, 1L);
//        long t2 = System.nanoTime();
//        System.out.println("DB query = " + (t2 - t1) / 1_000_000.0 + " ms");
//        session1.close();
//
//        Session session2 = sessionFactory.openSession();
//        long t3 = System.nanoTime();
//        Job job2 = session2.get(Job.class, 1L);
//        long t4 = System.nanoTime();
//        System.out.println("Cache query = " + (t4 - t3) / 1_000_000.0 + " ms");
//        session2.close();
    }
}
