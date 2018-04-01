package com.jackeryjam.proglang.exp1;

import com.jackeryjam.proglang.exp1.rawStudent.RawStudentController;
import com.jackeryjam.proglang.exp1.utils.Msg;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RawStudentControllerTest {
    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.standaloneSetup(new RawStudentController()).build();
    }

    @Test
    public void testRawStudentController() throws Exception{
        RequestBuilder request = null;
        //1、构建一个get请求.

        request = MockMvcRequestBuilders.get("/raw/student");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        System.out.println(result);
                    }
                });

        System.out.println("UserControllerTest.testUserController().get");
    }
}
