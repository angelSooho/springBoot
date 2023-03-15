package com.ll.basic1.repository;

import com.ll.basic1.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m from Member m where m.id=:id")
    public Member findByMember(@Param("id") Long id);
}
