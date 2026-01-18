package com.example.abcd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.event.id = :eventId")
    List<Attendance> findByEventId(@Param("eventId") Long eventId);

    @Query("SELECT a FROM Attendance a WHERE a.event.id = :eventId AND a.member.id = :memberId")
    Attendance findByEventIdAndMemberId(@Param("eventId") Long eventId,
                                        @Param("memberId") Long memberId);
}
