package com.example.homeyAPP.Business.propertyBusiness;


import com.example.homeyAPP.Domain.Entities.actors.Agent;
import com.example.homeyAPP.Domain.Entities.properties.Apartment;
import com.example.homeyAPP.Domain.Entities.properties.House;
import com.example.homeyAPP.Repositories.AgentRepository;
import com.example.homeyAPP.Repositories.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApartmentService {

    final ApartmentRepository apartmentRepository;
    final AgentRepository agentRepository;

    public Apartment addApartment(ApartementRequest reqApartment) {
        Optional<Agent> a = agentRepository.findAgentByEmail(reqApartment.getOwner_id());
        Agent agent = a.isPresent() ? a.get() : null;
        Apartment apartment = new Apartment(

                reqApartment.getType(),
                reqApartment.getStatus(),
                reqApartment.getPrice(),
                reqApartment.getSize(),
                reqApartment.getRoomsNum(),
                reqApartment.getBathroomsNum(),
                reqApartment.getFloor(),
                agent,
                reqApartment.getLatitude(),
                reqApartment.getLongitude()
        );
        return apartmentRepository.save(apartment);
    }

    public Apartment updateApartment(Long id, ApartementRequest reqApartment) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        Optional<Agent> a = agentRepository.findAgentByEmail(reqApartment.getOwner_id());
        Agent agent = a.isPresent() ? a.get() : null;
        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            apartment.setType(reqApartment.getType());
            apartment.setStatus(reqApartment.getStatus());
            apartment.setPrice(reqApartment.getPrice());
            apartment.setSize(reqApartment.getSize());
            apartment.setRoomsNum(reqApartment.getRoomsNum());
            apartment.setBathroomsNum(reqApartment.getBathroomsNum());
            apartment.setFloor(reqApartment.getFloor());
            apartment.setOwner_id(agent);

            return apartmentRepository.save(apartment);
        } else {
            throw new ResourceNotFoundException("Apartment not found with id: " + id);
        }
    }

    public void deleteApartment(Long id) {
        Optional<Apartment> optionalHouse = apartmentRepository.findById(id);
        if (optionalHouse.isPresent()) {
            apartmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Apartment not found with id: " + id);
        }
    }

    public List<Apartment> getAllApartment () {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> getApartmentById(Long id) {
        return apartmentRepository.findById(id);
    }

    public Apartment updateApartmentImage(Long id, String imgUrl) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            apartment.setImages(imgUrl);

            return apartmentRepository.save(apartment);
        } else {
            throw new ResourceNotFoundException("apartment not found with id: " + id);
        }
    }
    public String uploadImageToFileSystem(MultipartFile file, Long id) throws IOException {
        String uploadDir = "C:/Users/Asus/Downloads/homeyAPP/uploads/";

        String filePath=uploadDir+file.getOriginalFilename();

        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (apartment.isPresent()){
            Apartment selectedApartment = apartment.get();
            file.transferTo(new File(filePath));
            updateApartmentImage(selectedApartment.getId(),filePath);
        }else {
            return "error";
        }

        if (apartment != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }



    public List<byte[]> downloadImageFromFileSystem(Long id) throws IOException {
        Optional<Apartment> optionalApartment = getApartmentById(id);
        List<byte[]> imageBytesList = new ArrayList<>();

        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            List<String> imagePaths = apartment.getImages();

            for (String imagePath : imagePaths) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
                imageBytesList.add(imageBytes);
            }
        }
        System.out.println(imageBytesList);
        return imageBytesList;
    }
}
