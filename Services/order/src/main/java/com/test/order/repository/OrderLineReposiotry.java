package com.test.order.repository;


import com.test.order.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineReposiotry  extends JpaRepository<OrderLine,Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);
}
