package com.test3.dfs_3.repository;

import com.test3.dfs_3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByEid(String eid);

    List<Member> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    List<Member> findByLevel(String level);

    List<Member> findByLocation(String location);

    List<Member> findByStatus(String status);

    @Query(value = "SELECT * FROM Member m JOIN m.skills s WHERE s.key = :skillName", nativeQuery = true)
    List<Member> findBySkillsContainingKey(String skillName);



}