package com.siwyus.qrcontainer.controller;

import com.siwyus.qrcontainer.dto.ItemRequest;
import com.siwyus.qrcontainer.model.Item;
import com.siwyus.qrcontainer.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<String> addItemToContainer(@RequestBody ItemRequest item) {
        try {
            itemService.addItemToContainer(item);
            return ResponseEntity.ok("Item added to container successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add item to container: " + e.getMessage());
        }
    }

    @GetMapping("/container/{containerId}")
    public ResponseEntity<List<Item>> getItemsByContainer(@PathVariable String containerId) {
        List<Item> items = itemService.getItemsByContainerId(UUID.fromString(containerId));
        return ResponseEntity.ok(items);
    }
}
