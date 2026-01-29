package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/burger")
public class BurgerController {
    
    private BurgerDao burgerDao;
    
    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }
    
    @GetMapping
    public List<Burger> findAll() {
        log.info("GET /burger - Finding all burgers");
        return burgerDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        log.info("GET /burger/{} - Finding burger by id", id);
        BurgerValidation.validateId(id);
        return burgerDao.findById(id);
    }
    
    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        log.info("POST /burger - Saving burger: {}", burger.getName());
        BurgerValidation.validateBurger(burger);
        return burgerDao.save(burger);
    }
    
    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        log.info("PUT /burger - Updating burger with id: {}", burger.getId());
        BurgerValidation.validateBurgerForUpdate(burger);
        BurgerValidation.validateId(burger.getId());
        return burgerDao.update(burger);
    }
    
    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        log.info("DELETE /burger/{} - Removing burger", id);
        BurgerValidation.validateId(id);
        return burgerDao.remove(id);
    }
    
    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable Integer price) {
        log.info("GET /burger/price/{} - Finding burgers by price", price);
        return burgerDao.findByPrice(price);
    }
    
    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        log.info("GET /burger/breadType/{} - Finding burgers by bread type", breadType);
        return burgerDao.findByBreadType(breadType);
    }
    
    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        log.info("GET /burger/content/{} - Finding burgers by content", content);
        return burgerDao.findByContent(content);
    }
}
