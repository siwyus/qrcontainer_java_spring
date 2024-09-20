package com.siwyus.qrcontainer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemRequest {
    private String name;
    private String description;
    private Integer quantity;
    private String containerId;
}
