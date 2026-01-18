package com.example.abcd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.club.id = :clubId")
    List<Event> findByClubId(@Param("clubId") Long clubId);

    @Query("SELECT e FROM Event e WHERE DATE(e.datetime) = :date")
    List<Event> findByDate(@Param("date") LocalDate date);
}
