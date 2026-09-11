package de.ait.warehouse.service.interfaces;

import de.ait.warehouse.dto.delivery.DeliveryDto;
import de.ait.warehouse.dto.delivery.DeliverySaveDto;

import java.util.List;

public interface DeliveryService {

    DeliveryDto save(DeliverySaveDto dto);

    List<DeliveryDto> findAllDeliveries();
}
