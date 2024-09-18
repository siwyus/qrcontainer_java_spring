package com.siwyus.qrcontainer.repository;

import com.siwyus.qrcontainer.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ContainerRepository extends JpaRepository<Container, UUID> {
}