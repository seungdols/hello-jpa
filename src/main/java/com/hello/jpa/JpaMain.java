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
            Team team = new Team();
            team.setName("Team1");


            Member member1 = new Member();
            member1.setCreatedBy("user1");
            member1.setCreatedAt(LocalDateTime.now());
            member1.setTeam(team);
            Member member2 = new Member();
            member2.setCreatedBy("user2");
            member2.setCreatedAt(LocalDateTime.now());

            entityManager.persist(member1);
            entityManager.persist(member2);

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

            Member findMember = entityManager.find(Member.class, member1.getId());
            System.out.println("findMember.team: " + findMember.getTeam().getClass());
////            entityManager.close();
//            System.out.println("isLoaded: " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember));
////            Hibernate.initialize(findMember);
//            System.out.println("findMember: " + findMember.getClass());
//
//            Member findMember1 = entityManager.find(Member.class, member1.getId());
//            Member findMember2 = entityManager.find(Member.class, member2.getId());
            // == 비교 금지
//            System.out.println("findMember1 == findMember2: " + (findMember1 == findMember2));
//            System.out.println(findMember1 instanceof Member);
//            System.out.println(findMember2 instanceof Member);
//
//            System.out.println("findMember1 " + findMember1.getClass());
//            System.out.println("findMember.id: " + findMember.getId());
//            System.out.println("findMember.name: " + findMember.getName());
//            System.out.println("findMember1 == findMember: " + (findMember1 == findMember));
//            System.out.println("findMember: " + findMember.getClass());

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("exception: " + e);
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();
    }
}
