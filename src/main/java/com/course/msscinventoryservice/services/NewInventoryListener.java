package com.course.msscinventoryservice.services;

import com.course.msscinventoryservice.config.JmsConfig;
import com.course.msscinventoryservice.domain.BeerInventory;
import com.course.msscinventoryservice.repositories.BeerInventoryRepository;
import commn.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent newInventoryEvent) {
        log.debug("Got new Inventory");
//TODO add records to db
        beerInventoryRepository.save(BeerInventory.builder()
                .id(newInventoryEvent.getBeerDto().getUuid())
                .upc(newInventoryEvent.getBeerDto().getUpc())
                .quantityOnHand(newInventoryEvent.getBeerDto().getQuantityOnHand())
                .build());

    }
}
