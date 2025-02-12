package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

     //test that return strings

    //test for adding two binary numbers; the result as a string
    @Test
    public void addTest() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    //test for adding two binary numbers with empty operands; the result as a string
    @Test
    public void addTestEmpty() throws Exception {
        this.mvc.perform(get("/add").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    //test for AND operation given two binary numbers
    @Test
    public void andTest() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10"));
    }

    //test for OR operation given two binary numbers
    @Test
    public void orTest() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    //test for multiply operation given two binary numbers
    @Test
    public void multiplyTest() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000110"));
    }

    //test for multiply operation given empty operands
    @Test
    public void multiplyTestEmpty() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "").param("operand2", ""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

     //tests that return JSONs

    //test for adding two binary numbers; the result in JSON format
    @Test
    public void addTestJSON() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    //test for multiplying two binary numbers; the result in JSON format
    @Test
    public void testMultiplication() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000110))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    //test for OR operation on two binary numbers with empty operands; the result in JSON format
    @Test
    public void testOrWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","").param("operand2",""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //test for AND operation on two binary numbers with empty operands; the result in JSON format
    @Test
    public void testAndWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","").param("operand2",""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    //test for OR operation on two binary numbers; the result in JSON format
    @Test
    public void testOr() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    //test for multiplying two binary numbers with empty operands; the result in JSON format
    @Test
    public void testMultiplyWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1","").param("operand2",""))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }

    //test for AND operation on two binary numbers; the result in JSON format
    @Test
    public void testAnd() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    //test for adding two binary numbers with empty operands; the result in JSON format
    @Test
    public void testAddJSONWithEmptyOperands() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

}
