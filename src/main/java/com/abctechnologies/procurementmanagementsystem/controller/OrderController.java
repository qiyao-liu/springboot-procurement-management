package com.abctechnologies.procurementmanagementsystem.controller;

import com.abctechnologies.procurementmanagementsystem.pojo.Order;
import com.abctechnologies.procurementmanagementsystem.pojo.PageBean;
import com.abctechnologies.procurementmanagementsystem.pojo.Result;
import com.abctechnologies.procurementmanagementsystem.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @GetMapping
    public Result page(@RequestParam(required = false) String materialName,
                       @RequestParam(required = false) String supplierName,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("read orders, parameters: {}, {}, {}, {}", materialName, supplierName, page, pageSize);

        PageBean pageBean = orderService.page(materialName, supplierName, page, pageSize);

        return Result.success(pageBean);
    }


    @PostMapping
    public Result add(@RequestBody Order order) {
        log.info("adding an order: {}", order);
        orderService.add(order);
        return Result.success();
    }


    @PutMapping
    public Result update(@RequestBody Order order) {
        log.info("updating an order{}", order);
        orderService.update(order);
        return Result.success();
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("deleting an order by id:{}", id);
        orderService.delete(id);
        return Result.success();
    }
}