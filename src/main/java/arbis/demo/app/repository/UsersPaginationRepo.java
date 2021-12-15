package arbis.demo.app.repository;

import arbis.demo.app.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersPaginationRepo extends PagingAndSortingRepository<Users,Long> {

    Slice<Users> findByFullName(String firstName, Pageable pageable);

}
