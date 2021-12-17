package arbis.demo.app.service;

import arbis.demo.app.domain.Users;
import arbis.demo.app.pdfService.PdfService;
import arbis.demo.app.repository.UsersPaginationRepo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class UsersService implements UsersServiceImpl{

    @Autowired
    UsersPaginationRepo repo;

    @Autowired
    PdfService pdfService;

    @Override
    public Slice<Users> findByFirstName(String fullName, Pageable pageable) {
        return repo.findByFullName(fullName,pageable);
    }

    @Override
    public JasperPrint exportPdf() throws JRException, SQLException, IOException {
      return pdfService.exportPdfFile();
    }

}
