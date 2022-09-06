package com.sisa.endpoint.controller;

import java.util.HashMap;
import java.util.Map;

import com.sisa.endpoint.model.EndpointModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sisa/v.0.01/products")
public class EndpointController {
    private static Map<String, EndpointModel> endpointRepo = new HashMap<>();
    static {
        EndpointModel headset = new EndpointModel();
        headset.setId("1");
        headset.setName("Headset");
        endpointRepo.put(headset.getId(), headset);

        EndpointModel keyboard = new EndpointModel();
        keyboard.setId("2");
        keyboard.setName("Keyboard");
        endpointRepo.put(keyboard.getId(), keyboard);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        endpointRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody EndpointModel product) {
        endpointRepo.remove(id);
        product.setId(id);
        endpointRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<Object> createProduct(@RequestBody EndpointModel product) {
        endpointRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(endpointRepo.values(), HttpStatus.OK);
    }
}