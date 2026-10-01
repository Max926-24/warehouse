package de.ait.warehouse.service;

import de.ait.warehouse.domain.Delivery;
import de.ait.warehouse.domain.Item;
import de.ait.warehouse.domain.Supplier;
import de.ait.warehouse.dto.delivery.DeliveryDto;
import de.ait.warehouse.dto.delivery.DeliverySaveDto;
import de.ait.warehouse.dto.mapping.DeliveryMapper;
import de.ait.warehouse.exceptions.types.EntityNotFoundException;
import de.ait.warehouse.exceptions.types.PriceMismatchException;
import de.ait.warehouse.repository.DeliveryRepository;
import de.ait.warehouse.repository.ItemRepository;
import de.ait.warehouse.repository.SupplierRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private DeliveryMapper mapper;

    @InjectMocks
    private DeliveryServiceImpl service;

    @Test
    void save_shouldIncreaseItemQuantity_whenPriceMatches() {


        Item item = new Item();
        item.setId(1L);
        item.setPrice(new BigDecimal("12.99"));
        item.setQuantity(50);

        Supplier supplier = new Supplier();
        supplier.setId(2L);

        DeliverySaveDto saveDto = new DeliverySaveDto();
        saveDto.setItemId(1L);
        saveDto.setSupplierId(2L);
        saveDto.setPricePerUnit(new BigDecimal("12.99"));
        saveDto.setQuantity(20);
        saveDto.setDeliveryDate(LocalDateTime.now());

        DeliveryDto expectedDto = new DeliveryDto();
        expectedDto.setId(1L);
        expectedDto.setItemId(1L);
        expectedDto.setSupplierId(2L);
        expectedDto.setPricePerUnit(new BigDecimal("12.99"));
        expectedDto.setQuantity(20);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(supplierRepository.findById(2L)).thenReturn(Optional.of(supplier));
        when(mapper.mapEntityToDTo(any(Delivery.class))).thenReturn(expectedDto);


        DeliveryDto result = service.save(saveDto);

        assertThat(result).isEqualTo(expectedDto);
        assertThat(item.getQuantity()).isEqualTo(70);
        verify(itemRepository).save(item);
        verify(deliveryRepository).save(any(Delivery.class));


    }

    @Test
    void save_shouldThrowPriceMismatchException_whenPriceDoesNotMatch() {

        Item item = new Item();
        item.setId(1L);
        item.setPrice(new BigDecimal("12.99"));
        item.setQuantity(50);

        Supplier supplier = new Supplier();
        supplier.setId(2L);

        DeliverySaveDto saveDto = new DeliverySaveDto();
        saveDto.setItemId(1L);
        saveDto.setSupplierId(2L);
        saveDto.setPricePerUnit(new BigDecimal("15.00"));
        saveDto.setQuantity(20);
        saveDto.setDeliveryDate(LocalDateTime.now());

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(supplierRepository.findById(2L)).thenReturn(Optional.of(supplier));

        assertThrows(PriceMismatchException.class, () -> service.save(saveDto));

        verify(itemRepository, never()).save(any());
        verify(deliveryRepository, never()).save(any());

    }

    @Test
    void save_shouldThrowEntityNotFoundException_whenItemNotFound() {

        DeliverySaveDto saveDto = new DeliverySaveDto();
        saveDto.setItemId(1L);
        saveDto.setSupplierId(2L);
        saveDto.setPricePerUnit(new BigDecimal("12.99"));
        saveDto.setQuantity(20);
        saveDto.setDeliveryDate(LocalDateTime.now());

        when(itemRepository.findById(1l)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.save(saveDto));

        verifyNoInteractions(supplierRepository);
        verify(itemRepository, never()).save(any());
        verify(deliveryRepository, never()).save(any());


    }


}

