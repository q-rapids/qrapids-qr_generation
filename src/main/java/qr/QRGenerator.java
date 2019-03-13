package qr;

import qr.models.Alert;
import qr.repository.QRPatternsRepository;
import qr.models.QualityRequirementPattern;

import java.util.List;

public class QRGenerator {

    private QRPatternsRepository qrPatternsRepository;
    private List<QualityRequirementPattern> QRPlist;

    public QRGenerator (String urlBase) {
        qrPatternsRepository = new QRPatternsRepository(urlBase);
    }

    public boolean existsQRPattern(Alert alert){
        QRPlist = qrPatternsRepository.getQRPatterns(alert.getCategory());
        return !QRPlist.isEmpty();
    }

    public List<QualityRequirementPattern> generateQRs(Alert alert){
        QRPlist = qrPatternsRepository.getQRPatterns(alert.getCategory());
        return QRPlist;
    }
}
