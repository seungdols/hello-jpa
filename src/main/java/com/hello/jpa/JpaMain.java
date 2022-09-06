package com.hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {

            Member member = new Member();
            member.setCreatedBy("user1");
            member.setCreatedAt(LocalDateTime.now());

            entityManager.persist(member);

            Movie movie = new Movie();
            movie.setDirector("AA");
            movie.setActor("DDD");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);
            entityManager.persist(movie);

            entityManager.flush();
            entityManager.clear();

            Movie findMovie = entityManager.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie.getName());

            Member findMember = entityManager.getReference(Member.class, member.getId());
            System.out.println("findMember: " + findMember.getClass());
            System.out.println("findMember.id: " + findMember.getId());
            System.out.println("findMember.name: " + findMember.getName());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
