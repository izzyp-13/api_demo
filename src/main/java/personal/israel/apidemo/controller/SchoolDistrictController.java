package personal.israel.apidemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.israel.apidemo.models.SchoolDistrict;
import personal.israel.apidemo.repository.SchoolDistrictRepository;

import java.util.List;

@RestController
public class SchoolDistrictController {
    private final SchoolDistrictRepository repository;

    public SchoolDistrictController(SchoolDistrictRepository repository) {
        this.repository = repository;
    }
    /**
     * Create a new school district
     */
    @PostMapping("/districts")
    public ResponseEntity<SchoolDistrict> createDistrict(@RequestBody SchoolDistrict district) {
        SchoolDistrict savedDistrict = repository.save(district);
        return ResponseEntity.ok(savedDistrict);
    }

    /**
     * Get a list of all school districts
     */
    @GetMapping("/districts")
    public ResponseEntity<List<SchoolDistrict>> getAllDistricts() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Get one school district by ID
     */
    @GetMapping("/districts/{id}")
    public ResponseEntity<SchoolDistrict> getDistrictById(@PathVariable Long id) {
        SchoolDistrict schoolDistrict = repository.findById(id);
        if (schoolDistrict != null) {
            return ResponseEntity.ok(schoolDistrict);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
