package arbis.demo.app.pdfService;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    public JasperPrint exportPdfFile() throws SQLException, JRException, IOException {

        Connection conn = jdbcTemplate.getDataSource().getConnection();

        String path = resourceLoader.getResource("classpath:users.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();

        //adding parameter on jasper reports
        parameters.put("user_name","Arbis Malasi");
//        parameters.put("address","Astir");
//        parameters.put("number","0675145933");

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

        return print;
    }
}
