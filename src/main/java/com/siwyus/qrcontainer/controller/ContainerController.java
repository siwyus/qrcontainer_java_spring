package com.siwyus.qrcontainer.controller;

import com.siwyus.qrcontainer.dto.ContainersRequest;
import com.siwyus.qrcontainer.model.Container;
import com.siwyus.qrcontainer.services.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/containers")
public class ContainerController {

    private final ContainerService containerService;

    @PostMapping
    public ResponseEntity<Container> createContainer(@RequestBody ContainersRequest request) {
        try {
            Container container = containerService.createContainer(request.getName());
            return new ResponseEntity<>(container, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> getQRCode(@PathVariable UUID id) {
        Optional<Container> containerData = containerService.getContainerById(id);

        if (containerData.isPresent()) {
            byte[] qrCode = containerData.get().getQrCode();
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_TYPE, "image/png");
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Container> getContainer(@PathVariable UUID id) {
        Optional<Container> containerData = containerService.getContainerById(id);

        return containerData.map(container -> new ResponseEntity<>(container, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
