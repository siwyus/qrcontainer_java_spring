package com.siwyus.qrcontainer.services;

import com.siwyus.qrcontainer.model.Container;
import com.siwyus.qrcontainer.repository.ContainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContainerService {

    private final ContainerRepository containerRepository;
    private final QRCodeService qrCodeService;

    public Container createContainer(String name) throws Exception {
        UUID uuid = UUID.randomUUID();
        byte[] qrCode = qrCodeService.generateQRCode(uuid.toString());

        Container container = Container.builder()
                .id(uuid)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .name(name)
                .qrCode(qrCode)
                .build();

        return containerRepository.save(container);
    }

    public Optional<Container> getContainerById(UUID id) {
        return containerRepository.findById(id);
    }
}
