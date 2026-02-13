package com.SwSoftware.OptiRouteTracker.controllers.inventory;

import com.SwSoftware.OptiRouteTracker.constants.ApiPaths;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoCreateInventory;
import com.SwSoftware.OptiRouteTracker.dtos.dtosEntities.inventory.DtoUpdateInventory;
import com.SwSoftware.OptiRouteTracker.dtos.responseApi.DtoResponseApi;
import com.SwSoftware.OptiRouteTracker.interfaces.IInventoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.baseApi + "/inventory")
@AllArgsConstructor
public class InventoryController {

    private final IInventoryService iInventoryService;

    @PostMapping()
    public ResponseEntity createInventory(@Valid @RequestBody DtoCreateInventory data){
        return ResponseEntity.status(HttpStatus.CREATED).body(DtoResponseApi.builder()
                .status(HttpStatus.CREATED.value())
                .message("Inventory created")
                .data(iInventoryService.createInventory(data))
                .build()
        );
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity getAllDataInventory(@PathVariable Long idInventory){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("obtained inventory")
                .data(iInventoryService.getAllDataInventory(idInventory))
                .build()
        );
    }

    @GetMapping()
    public ResponseEntity getAllInventories(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("obtained inventories")
                .data(iInventoryService.getAllInventories(page,size))
                .build()
        );
    }

    @DeleteMapping("/{idProduct}/{idInventory}")
    public ResponseEntity deleteProductInventory(@PathVariable Long idProduct, @PathVariable Long idInventory){
        iInventoryService.removeInventoryProduct(idProduct,idInventory);
        return  ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Removed inventory product")
                .build()
        );
    }


    @DeleteMapping("/{idInventory}")
    public ResponseEntity deleteInventory(@PathVariable Long idInventory){
        iInventoryService.removeInventory(idInventory);
        return  ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Removed inventory")
                .build()
        );
    }

    @PutMapping()
    public ResponseEntity updateInventory(@Valid @RequestBody DtoUpdateInventory data){
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponseApi.builder()
                .status(HttpStatus.OK.value())
                .message("Updated inventory")
                .data(iInventoryService.updateInventory(data))
                .build()
        );
    }
}
