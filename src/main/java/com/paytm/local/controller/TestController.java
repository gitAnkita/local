package com.paytm.local.controller;

import com.paytm.local.datasource.dataservice.UserDataService;
import com.paytm.local.datasource.model.User;
import com.paytm.local.dto.MyRunnable;
import com.paytm.local.dto.UserDetailsDTO;
import com.paytm.local.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/local")
public class TestController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private Producer producer;

    @RequestMapping("/parallel-run")
    public String parallelRun(@RequestParam(defaultValue = "1") Integer count){

        for (int i=0;i<count;i++){
            User user = new User();
            user.setName("test"+i);
            user.setDefaultVpa("vpa"+i);
            user.setStatus("INACTIVE");
            Runnable task = new MyRunnable(userDataService,user,i+1);
            CompletableFuture.runAsync(task);
        }

        return "completed";
    }

    @RequestMapping("/produce-kafka")
    public String pushTokafka(String msg){
        producer.sendMessage(msg);
        return "done";
    }


}
