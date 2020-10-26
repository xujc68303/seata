package com.xjc.seatatcc.storage.controller;

import com.xjc.seatatcc.storage.service.StorageService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Version 1.0
 * @ClassName StorageController
 * @Author jiachenXu
 * @Date 2020/10/26
 * @Description
 */
@RequestMapping("/storage")
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/decrease")
    public boolean prepare(@RequestBody BusinessActionContext actionContext, @RequestParam("userId") String userId, @RequestParam("count") Integer count) {
        return storageService.decrease(actionContext.getXid( ), userId, count);
    }

    @RequestMapping("/commit")
    public boolean commit(@RequestBody BusinessActionContext actionContext) {
        return storageService.commit(actionContext.getXid( ));

    }

    @RequestMapping("/rollback")
    public boolean rollback(@RequestBody BusinessActionContext actionContext) {
        return storageService.rollback(actionContext.getXid( ));
    }


}
