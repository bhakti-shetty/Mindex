/*
 * Author: Bhakti Shetty
 * */
package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationImplTest {
    private String employeeUrl;
    private String createCompensationUrl;
    private String readCompensationUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployee;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        createCompensationUrl = "http://localhost:" + port + "/compensation";
        readCompensationUrl = "http://localhost:" + port + "/compensation/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @After
    public void teardown() {
        employeeUrl = null;
        createCompensationUrl = null;
        readCompensationUrl = null;

        testEmployee = null;
    }

    @Test
    public void testCreateReadUpdate() {
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary("100000 GPD");
        testCompensation.setEffectiveDate(Instant.parse("2022-01-17T03:48:00Z"));

        // Create checks
        ResponseEntity createdCompensationResponse = restTemplate.postForEntity(createCompensationUrl, testCompensation, Compensation.class);
        assertEquals(HttpStatus.OK, createdCompensationResponse.getStatusCode());
        Compensation createCompensation = (Compensation)createdCompensationResponse.getBody();
        assertNotNull(createCompensation);
        assertEquals(testCompensation, createCompensation);

        // Read checks
        ResponseEntity CompensationResponse = restTemplate.getForEntity(readCompensationUrl, Compensation.class, createCompensation.getEmployee().getEmployeeId());
        assertEquals(HttpStatus.OK, CompensationResponse.getStatusCode());
        Compensation readCompensation = (Compensation)CompensationResponse.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation, createCompensation);
    }
}
