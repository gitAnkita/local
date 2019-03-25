package com.paytm.local.controller;

import com.paytm.local.exceptionhandler.MethodExceptionHandler;
import com.paytm.local.exceptionhandler.TestMethodAAdvice;
import com.paytm.local.datasource.dataservice.UserDataService;
import com.paytm.local.datasource.model.User;
import com.paytm.local.dto.MyRunnable;
import com.paytm.local.kafka.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/local")
public class TestController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private Producer producer;

    @RequestMapping("/testA")
    @MethodExceptionHandler(adviceClass = TestMethodAAdvice.class)
    public String testA(@RequestParam(defaultValue = "0") Integer input){
        if(input == 1){
            throw new ArithmeticException("/ by 0");
        }

        if(input == 2){
            throw new NullPointerException("/ by 0");
        }

        return "testA success";
    }

    @RequestMapping("/testB")
    public String testB(@RequestParam(defaultValue = "0") Integer input){
        if(input == 1){
            throw new ArithmeticException("/ by 0");
        }

        if(input == 2){
            throw new NullPointerException("/ by 0");
        }

        return "testB success";
    }



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
