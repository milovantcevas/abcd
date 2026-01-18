package com.example.abcd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MemberRepository extends JpaRepository<Member, Long> {}
