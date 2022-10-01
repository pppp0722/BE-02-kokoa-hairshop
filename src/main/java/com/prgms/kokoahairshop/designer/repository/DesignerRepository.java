package com.prgms.kokoahairshop.designer.repository;

import com.prgms.kokoahairshop.designer.entity.Designer;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DesignerRepository extends JpaRepository<Designer, Long> {

    @Query("select distinct d from Designer d join fetch d.reservationTimes r "
        + "where d.hairshop.id = :hairshopId and r.date = :date and r.reserved = false")
    List<Designer> findDesignerFetchJoinByHairshopIdAndDate(@Param("hairshopId") Long hairshopId,
        @Param("date") LocalDate date);

    @Query("select d from Designer d join fetch d.hairshop h")
    List<Designer> findAllDesignerFetchJoin();

    @Query(value = "SELECT DISTINCT d from Designer d JOIN FETCH d.reservations r "
        + "WHERE d.hairshop.id = :hairshopId AND r.date = :date")
    List<Designer> findByHairshopIdAndDate(Long hairshopId, LocalDate date);

    List<Designer> findByHairshopId(Long hairshopId);

}
