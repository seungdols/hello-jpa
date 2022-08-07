package com.hello.jpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            // insert
            Member member = new Member();
            member.setId(3L);
            member.setName("seungdols3");

            Member findMember = entityManager.find(Member.class, 3L);
            System.out.println("member.name: " + member.getName());
            System.out.println("findMember.name: " + findMember.getName());

            entityManager.persist(member);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
