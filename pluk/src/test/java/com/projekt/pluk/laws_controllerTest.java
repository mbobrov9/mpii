package com.projekt.pluk;

import com.projekt.pluk.controllers.laws_controller;
import com.projekt.pluk.models.laws;
import com.projekt.pluk.repos.laws_repo;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.ui.Model;


import java.util.Collection;
import java.util.List;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource("/application-test.properties")
@WithUserDetails(value = "123")

public class laws_controllerTest {
    WebDriver driver = new EdgeDriver();
    @Before
    public void setUp()
    {

    }
    @Autowired
    private MockMvc mockMvc;



    @Autowired
    private laws_controller law;
    @Autowired
    private laws_repo Laws;
    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(authenticated())
                .andExpect(status().isOk());
    }
    @Test
    public void testik()
    {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\mbobr\\Downloads\\edgedriver_win64\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("http://localhost:8080/");
    }
}
