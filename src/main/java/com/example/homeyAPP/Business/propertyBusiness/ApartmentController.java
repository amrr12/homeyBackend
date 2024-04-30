package com.example.homeyAPP.Business.propertyBusiness;

import com.example.homeyAPP.Domain.Entities.properties.Apartment;
import com.example.homeyAPP.Domain.Entities.properties.House;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apartment")
@RequiredArgsConstructor
public class ApartmentController {

    final ApartmentService apartmentService;

    @GetMapping("/allapartments")
    public List<Apartment> getAllHouses() {
        return  apartmentService.getAllApartment();
    }

    @GetMapping("/apartmentbyid/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long id) {
        Optional<Apartment> apartment = apartmentService.getApartmentById(id);
        if (apartment.isPresent()) {
            return ResponseEntity.ok(apartment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addApartment(@RequestBody ApartementRequest apartementRequest) {
        apartmentService.addApartment(apartementRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Apartment added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateApartment(@PathVariable Long id, @RequestBody ApartementRequest apartementRequest) {
            apartmentService.updateApartment(id, apartementRequest);
        return ResponseEntity.ok("House updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApartment(@PathVariable Long id) {
        apartmentService.deleteApartment(id);
        return ResponseEntity.ok("House deleted successfully");
    }

    @PostMapping("/image/{apartmentid}")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file, @PathVariable Long apartmentid) throws IOException {
        String uploadImage = apartmentService.uploadImageToFileSystem(file,apartmentid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }


    @GetMapping("/image/{apartmentid}")
    public ResponseEntity<?> downloadImagesFromFileSystem(@PathVariable Long apartmentid) throws IOException {
        List<byte[]> imageDataList = apartmentService.downloadImageFromFileSystem(apartmentid);

        // Create a list of ResponseEntity objects, each representing an image
        List<ResponseEntity<byte[]>> imageResponses = imageDataList.stream()
                .map(imageData -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(imageData))
                .collect(Collectors.toList());

        // Return ResponseEntity objects as an array
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(imageResponses.toArray());
    }

}
