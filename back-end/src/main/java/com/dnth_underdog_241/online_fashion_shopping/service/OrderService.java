package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.dto.request.OrderCreateRequestDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderCreateResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetAllResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.dto.response.OrderGetResponseDto;
import com.dnth_underdog_241.online_fashion_shopping.exception.ResourcesNotFound;
import com.dnth_underdog_241.online_fashion_shopping.exception.UserNotFoundException;
import com.dnth_underdog_241.online_fashion_shopping.mapper.OrderMapper;
import com.dnth_underdog_241.online_fashion_shopping.model.CartProduct;
import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Customer;
import com.dnth_underdog_241.online_fashion_shopping.repository.CartProductRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.OrderRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.WebUserRepository;
import com.dnth_underdog_241.online_fashion_shopping.util.WebUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService
{
    private final OrderMapper orderMapper;
    private final WebUserUtil webUserUtil;
    private final WebUserRepository webUserRepository;
    private final CartProductRepository cartProductRepository;
    private final OrderRepository orderRepository;


    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto)
    {
        Order order = orderMapper.toOrder(orderCreateRequestDto);

        Customer customer = (Customer) webUserRepository
                .findById
                        (
                                webUserUtil.getAuthenticatedUserId()
                        )
                .orElseThrow(() -> new UserNotFoundException(webUserUtil.getAuthenticatedUserId()));

        orderCreateRequestDto
                .getCartProductIdList()
                        .forEach(cartProductId ->
                        {
                            CartProduct cartProduct= cartProductRepository.findById(cartProductId).orElseThrow(() -> new RuntimeException("Cart product not found"));

                            cartProduct.getVariantProduct().setStock(cartProduct.getVariantProduct().getStock() - cartProduct.getQuantity());
                        });

        customer.getOrders().add(order);
        order.setCustomer(customer);

        orderRepository.save(order);

        return orderMapper.toOrderCreateResponseDto(order);
    }


    public Page<OrderGetAllResponseDto> getAllOrders(Long id, Pageable pageable)
    {
        Page<Order> orders = orderRepository.findByCustomerId(id, pageable);

        return orders
                .map(orderMapper::toOrderGetAllResponseDto);
    }


    public OrderGetResponseDto getOrder(Long orderId)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourcesNotFound("Order not found"));

        return orderMapper.toOrderGetResponseDto(order);
    }


    public void deleteOrder(Long orderId)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourcesNotFound("Order not found"));

        orderRepository.delete(order);
    }
}
