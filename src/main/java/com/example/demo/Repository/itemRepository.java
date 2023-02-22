package com.example.demo.Repository;

import com.example.demo.domain.items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface itemRepository extends JpaRepository<items,Long> {
}
