package arbis.demo.app.service;

import arbis.demo.app.domain.Users;
import arbis.demo.app.repository.UsersPaginationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public class UsersService implements UsersServiceImpl{

    @Autowired
    UsersPaginationRepo repo;


    @Override
    public Slice<Users> findByFirstName(String fullName, Pageable pageable) {
        return repo.findByFullName(fullName,pageable);
    }
}
