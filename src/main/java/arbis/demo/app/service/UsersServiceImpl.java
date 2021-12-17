package arbis.demo.app.service;


import arbis.demo.app.domain.Users;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.io.IOException;
import java.sql.SQLException;

public interface UsersServiceImpl {

    Slice<Users> findByFirstName(String fullName , Pageable pageable);

    JasperPrint exportPdf() throws JRException, SQLException, IOException;
}
