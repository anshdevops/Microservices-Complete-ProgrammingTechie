package com.programmingtechie.orderservice.service;


import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {


    private final OrderRepo orderRepo;

   public void placeOrder(OrderRequest request){


       log.info("inside {} method with request as {}","placeOrder",request);



       Order order= new Order();
       order.setOrderNumber(UUID.randomUUID().toString());
       List<OrderLineItems> orderLineItemsList= request.getOrderLineItemsDtoList()
               .stream()
               .map(this::mapToDto).toList();

      order.setOrderLineItems(orderLineItemsList);
      orderRepo.save(order);



    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

       OrderLineItems orderLineItems= new OrderLineItems();
       orderLineItems.setPrice(orderLineItemsDto.getPrice());
       orderLineItems.setQuantity(orderLineItems.getQuantity());
       orderLineItems.setSkuCode(orderLineItems.getSkuCode());
       return orderLineItems;
    }
}
