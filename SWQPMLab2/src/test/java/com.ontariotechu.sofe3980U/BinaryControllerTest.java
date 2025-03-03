package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    //test for default GET request
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", ""))
            .andExpect(model().attribute("operand1Focused", false));
    }
    
    //test for GET request with parameter
    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
            .andExpect(model().attribute("operand1", "111"))
            .andExpect(model().attribute("operand1Focused", true));
    }

    //test for POST request with parameters for addition
    @Test
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand1", "111"));
    }

    //test for POST request with parameters for multiplication
    @Test
    public void testMultiplication() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100011"));
    }

    //test for POST request with parameters for OR operation
    @Test
    public void testOr() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","101"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "111"));
    }

    //test for addition with both operands empty
    @Test
    public void testEmptyOperandsAddition() throws Exception {
        this.mvc.perform(post("/").param("operand1","").param("operator","+").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"));
    }

    //test for invalid operator
   /* @Test
    public void testInvalidOperator() throws Exception {
        this.mvc.perform(post("/")
                        .param("operand1", "101")
                        .param("operator", "%")
                        .param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(view().name("Error"));
    }
    */
}
