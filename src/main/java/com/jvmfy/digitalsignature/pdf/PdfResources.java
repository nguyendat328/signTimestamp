package com.jvmfy.digitalsignature.pdf;

import com.jvmfy.digitalsignature.signature.SigningService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/api/pdf")
public class PdfResources {

    private final PdfService pdfService;
    private final SigningService signingService;
    private static final Logger log = LoggerFactory.getLogger(SigningService.class);

    public PdfResources(PdfService pdfService, SigningService signingService) {
        this.pdfService = pdfService;
        this.signingService = signingService;
    }

    @GetMapping(value = "/export", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity exportPdf() {
        try {
//            byte[] pdfToSign = this.pdfService.generatePdf();
            byte[] pdfToSign = fileToByteArray("D:\\test\\in.pdf");
            byte[] signedPdf = this.signingService.signPdf(pdfToSign);
            File filesigned = new File("D:\\test\\"
                    + UUID.randomUUID().toString() + ".pdf");
            FileOutputStream fop = new FileOutputStream(filesigned);
            fop.write(signedPdf);
            fop.flush();
            fop.close();
            return ResponseEntity.ok(Base64.getEncoder().encode(signedPdf));
        } catch (IOException e) {
            log.error("Cannot generate PDF file", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public byte[] fileToByteArray(String path) throws FileNotFoundException, IOException {
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
