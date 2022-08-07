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
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("seungdols2");

            Member member = entityManager.find(Member.class, 1L);
            member.setName("Seungdols");

            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class).getResultList();

            for (Member findMember : result) {
                System.out.println("member.name: " + findMember.getName());
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
