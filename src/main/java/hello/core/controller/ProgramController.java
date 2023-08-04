package hello.core.controller;

import hello.core.service.ProgramService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @GetMapping("/barcode")
    public String getBarcode(@RequestParam("patient_id") String patientId) {
        return programService.getBarcode(patientId);
    }
}
