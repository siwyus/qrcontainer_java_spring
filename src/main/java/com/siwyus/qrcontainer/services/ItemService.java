package com.siwyus.qrcontainer.services;

import com.siwyus.qrcontainer.dto.ItemRequest;
import com.siwyus.qrcontainer.model.Container;
import com.siwyus.qrcontainer.model.Item;
import com.siwyus.qrcontainer.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {
    private final ContainerService containerService;
    private final ItemRepository itemRepository;

    public void addItemToContainer(ItemRequest itemDTO) {
        Optional<Container> container = containerService.getContainerById(UUID.fromString(itemDTO.getContainerId()));


        if (container.isEmpty()) {
            throw new RuntimeException("Container with id " + itemDTO.getContainerId() + " not found.");
        }


        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .quantity(itemDTO.getQuantity())
                .container(container.get())
                .build();

        itemRepository.save(item);
    }

    public List<Item> getItemsByContainerId(UUID containerId) {
        return itemRepository.findByContainerId(containerId);
    }
}
