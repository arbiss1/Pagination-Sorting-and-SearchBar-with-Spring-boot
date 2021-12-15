package arbis.demo.app.service;


import arbis.demo.app.domain.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UsersServiceImpl {

    Slice<Users> findByFirstName(String fullName , Pageable pageable);


}
