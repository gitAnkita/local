package com.paytm.local.dto;

import com.paytm.local.datasource.dataservice.UserDataService;
import com.paytm.local.datasource.model.User;

public class MyRunnable implements Runnable {

    private UserDataService userDataService;

    private User user;

    private int taskNo;

    public MyRunnable(UserDataService userDataService,User user,int taskNo){
        this.userDataService = userDataService;
        this.user = user;
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        System.out.println("MyRunnable run start : "+taskNo);
        user = userDataService.addUser(user);
        user.setStatus("ACTIVE");
        user = userDataService.updateUser(user);
        User updatedUser = userDataService.findById(user.getId());
        System.out.println("MyRunnable run end : "+taskNo + " status: "+updatedUser.getStatus());
    }

}
