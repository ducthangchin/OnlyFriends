package com.ducthangchin.test;

import com.ducthangchin.App;
import com.ducthangchin.model.StatusUpdate;
import com.ducthangchin.model.StatusUpdateDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.Random;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebAppConfiguration
public class StatusTest {

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    @Test
    public void testSave() {
        System.out.println("stt");
        StatusUpdate status = new StatusUpdate("You look great today!");

        System.out.println();
        System.out.println("saving..");
        System.out.println();
        statusUpdateDao.save(status);
        System.out.println();
        System.out.println("saved..");
        System.out.println();




        assertNotNull("Non-null ID", status.getId());
        assertNotNull("Non-null Date", status.getAdded());

        StatusUpdate retrieved = statusUpdateDao.findOne(status.getId());

        //assertEquals("Matching StatusUpdate", status, retrieved);
    }



}





