package com.example.demo.Repository;

import com.example.demo.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
@Query(value =  " SELECT  id from cart where cart_owner_id = :userId", nativeQuery = true)
   Long getUserCart(@Param("userId") Long userId);

@Query(value =  "SELECT * from cart where cart_owner_id = :userId",nativeQuery = true)
   Page<Cart> getCartList(@Param("userId")long userId, Pageable pageable);
}
