package com.project.code.Service;

import com.project.code.Model.*;
import com.project.code.Repo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;


    public void saveOrder(PlaceOrderRequestDTO placeOrderRequest) {

        // Vérifier si le client existe déjà
        Customer existingCustomer = customerRepository
                .findByEmail(placeOrderRequest.getCustomerEmail());

        Customer customer = new Customer();
        customer.setName(placeOrderRequest.getCustomerName());
        customer.setEmail(placeOrderRequest.getCustomerEmail());
        customer.setPhone(placeOrderRequest.getCustomerPhone());

        if (existingCustomer == null) {
            customer = customerRepository.save(customer);
        } else {
            customer = existingCustomer;
        }

        // récupérer le store
        Store store = storeRepository
                .findById(placeOrderRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        // créer order
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomer(customer);
        orderDetails.setStore(store);
        orderDetails.setTotalPrice(placeOrderRequest.getTotalPrice());
        orderDetails.setDate(LocalDateTime.now());

        orderDetails = orderDetailsRepository.save(orderDetails);

        // récupérer la liste des produits
        List<PurchaseProductDTO> purchaseProducts =
                placeOrderRequest.getPurchaseProduct();

        for (PurchaseProductDTO productDTO : purchaseProducts) {

            OrderItem orderItem = new OrderItem();

            // récupérer inventory
            Inventory inventory = inventoryRepository
                    .findByProductIdandStoreId(
                            productDTO.getId(),
                            placeOrderRequest.getStoreId()
                    );

            // diminuer le stock
            inventory.setStockLevel(
                    inventory.getStockLevel() - productDTO.getQuantity()
            );

            inventoryRepository.save(inventory);

            // lier order
            orderItem.setOrder(orderDetails);

            // récupérer product
            Product product = productRepository
                    .findById(productDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            orderItem.setProduct(product);

            // définir quantité et prix
            orderItem.setQuantity(productDTO.getQuantity());
            orderItem.setPrice(productDTO.getPrice() * productDTO.getQuantity());

            // sauvegarder
            orderItemRepository.save(orderItem);
        }
    }
}