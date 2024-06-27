package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsBySchool_IdAndRoomNumberIgnoreCase(Integer id, String roomNumber);

    @Query(value = """
            SELECT r
            FROM Room r
            ORDER BY r.id
            LIMIT :limit
            OFFSET :offset""")
    List<Room> selectRoomsWithPagination(@Param("limit") long limit, @Param("offset") long offset);

    @Query(value = """
            SELECT r
            FROM Room r
            WHERE r.category = :category
            ORDER BY r.id
            LIMIT :limit
            OFFSET :offset""")
    List<Room> selectRoomsByCategoryWithPagination(@Param(value = "category") RoomCategory category,
                                                   @Param("limit") long limit, @Param("offset") long offset);

}