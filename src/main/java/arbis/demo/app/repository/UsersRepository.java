package arbis.demo.app.repository;

import arbis.demo.app.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM pagination.users WHERE full_name LIKE %:keyword% OR address LIKE %:keyword%  OR number LIKE %:keyword%")
    List<Users> search(@Param("keyword") String keyword);

    @Query(nativeQuery = true , value = "SELECT * FROM pagination.users WHERE full_name LIKE %:firstName%")
    List<Users> serachByFirstName(@Param("firstName") String firstName);
}
