package se.comhem.test.montyhall.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import se.comhem.test.montyhall.domain.MontyHallSimulator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MontyHallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MontyHallSimulator montyHallSimulator;


    @Test
    public void shouldReturnOK() throws Exception {
        this.mockMvc.perform(get("/simulate?strategy=SWITCH")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestOnNegativeNumberForIterationsReqParam() throws Exception {
        this.mockMvc.perform(get("/simulate?strategy=SWITCH&iterations=-1")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestOnToLargeNumberForIterationsReqParam() throws Exception {
        this.mockMvc.perform(get("/simulate?strategy=SWITCH&iterations=1000001")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestOnMissingReqParamStrategy() throws Exception {
        this.mockMvc.perform(get("/simulate")).andDo(print()).andExpect(status().isBadRequest());
    }
}