package com.javastore.catalog.adapters.in.messaging.dto;

import java.util.List;

public record OrderEventDTO(Long id, List<OrderItemEventDTO> items) {
}
