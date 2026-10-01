package de.ait.warehouse.service;

import de.ait.warehouse.domain.Item;
import de.ait.warehouse.domain.Sale;
import de.ait.warehouse.dto.mapping.SaleMapper;
import de.ait.warehouse.dto.sale.SaleDto;
import de.ait.warehouse.dto.sale.SaleSaveDto;
import de.ait.warehouse.exceptions.types.InsufficientStockException;
import de.ait.warehouse.repository.ItemRepository;
import de.ait.warehouse.repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceImplTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private SaleMapper mapper;

    @InjectMocks
    private SaleServiceImpl service;


    @Test
    void save_shouldDecreaseItemQuantity_whenStockIsSufficient() {

        Item item = new Item();
        item.setId(1L);
        item.setQuantity(50);

        SaleSaveDto saveDto = new SaleSaveDto();
        saveDto.setItemId(1L);
        saveDto.setQuantity(20);
        saveDto.setSaleDate(LocalDateTime.now());

        SaleDto expectedDto = new SaleDto();
        expectedDto.setId(1L);
        expectedDto.setItemId(1L);
        expectedDto.setQuantity(20);
        expectedDto.setSaleDate(saveDto.getSaleDate());

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(mapper.mapEntityToDto(any(Sale.class))).thenReturn(expectedDto);

        SaleDto result = service.save(saveDto);

        assertThat(result).isEqualTo(expectedDto);
        assertThat(item.getQuantity()).isEqualTo(30);

        verify(itemRepository).save(item);
        verify(saleRepository).save(any(Sale.class));


    }

    @Test
    void save_shouldThrowInsufficientStockException_whenQuantityExceedsStock() {

        Item item = new Item();
        item.setId(1L);
        item.setQuantity(10);

        SaleSaveDto saveDto = new SaleSaveDto();
        saveDto.setItemId(1L);
        saveDto.setQuantity(20);
        saveDto.setSaleDate(LocalDateTime.now());


        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        assertThrows(InsufficientStockException.class, () -> service.save(saveDto));

        verify(itemRepository, never()).save(any());
        verify(saleRepository, never()).save(any());

    }
}
