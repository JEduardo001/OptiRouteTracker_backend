package com.SwSoftware.OptiRouteTracker.controllers.inventory;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoCreateInventory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoInventoryWithoutProducts;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping()
    public ResponseEntity createInventory(@Valid @RequestBody DtoCreateInventory data){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Inventory created")
                .data(inventoryService.createInventory(data))
                .build()
        );
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity getAllDataInventory(@PathVariable Long idInventory){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("obtained inventory")
                .data(inventoryService.getAllDataInventory(idInventory))
                .build()
        );
    }

    @GetMapping()
    public ResponseEntity getAllInventories(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("obtained inventories")
                .data(inventoryService.getAllInventories(page,size))
                .build()
        );
    }

    @DeleteMapping("/{idProduct}/{idInventory}")
    public ResponseEntity deleteProductInventory(@PathVariable Long idProduct, @PathVariable Long idInventory){
        inventoryService.removeInventoryProduct(idProduct,idInventory);
        return  ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Removed inventory product")
                .build()
        );
    }


    @DeleteMapping("/{idInventory}")
    public ResponseEntity deleteInventory(@PathVariable Long idInventory){
        inventoryService.removeInventory(idInventory);
        return  ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Removed inventory")
                .build()
        );
    }

    @PutMapping("/{idInventory}")
    public ResponseEntity updateInventory(@Valid @RequestBody DtoInventoryWithoutProducts data){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated inventory")
                .data(inventoryService.updateInventory(data))
                .build()
        );
    }
}
