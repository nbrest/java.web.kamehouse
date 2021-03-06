package com.nicobrest.kamehouse.testmodule.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.nicobrest.kamehouse.commons.controller.AbstractControllerTest;
import com.nicobrest.kamehouse.commons.exception.KameHouseServerErrorException;
import com.nicobrest.kamehouse.commons.model.KameHouseGenericResponse;
import com.nicobrest.kamehouse.testmodule.service.TestSchedulerService;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

/**
 * Unit tests for TestSchedulerController class.
 *
 * @author nbrest
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@WebAppConfiguration
public class TestSchedulerControllerTest
    extends AbstractControllerTest<KameHouseGenericResponse, Object> {

  @InjectMocks
  private TestSchedulerController testSchedulerController;

  @Mock
  protected TestSchedulerService testSchedulerService;

  @Before
  public void beforeTest() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(testSchedulerController).build();
  }

  /**
   * Set sample job schedule successful test.
   */
  @Test
  public void setSampleJobTest() throws Exception {
    MockHttpServletResponse response = doPost("/api/v1/test-module/test-scheduler/sample-job" +
        "?delay=5400");
    KameHouseGenericResponse responseBody = getResponseBody(response,
        KameHouseGenericResponse.class);

    verifyResponseStatus(response, HttpStatus.CREATED);
    assertEquals("Scheduled sample job at the specified delay of 5400 seconds",
        responseBody.getMessage());
    verify(testSchedulerService, times(1)).scheduleSampleJob(Mockito.anyInt());
  }

  /**
   * Cancels sample job successful test.
   */
  @Test
  public void cancelSampleJobTest() throws Exception {
    when(testSchedulerService.cancelScheduledSampleJob()).thenReturn("Sample job cancelled");

    MockHttpServletResponse response = doDelete("/api/v1/test-module/test-scheduler/sample-job");
    KameHouseGenericResponse responseBody = getResponseBody(response,
        KameHouseGenericResponse.class);

    verifyResponseStatus(response, HttpStatus.OK);
    assertEquals("Sample job cancelled", responseBody.getMessage());
    verify(testSchedulerService, times(1)).cancelScheduledSampleJob();
  }

  /**
   * Cancels sample job error test.
   */
  @Test
  public void cancelSampleJobServerErrorTest() throws Exception {
    thrown.expect(NestedServletException.class);
    thrown.expectCause(IsInstanceOf.<Throwable> instanceOf(
        KameHouseServerErrorException.class));
    Mockito.doThrow(new KameHouseServerErrorException("")).when(testSchedulerService)
        .cancelScheduledSampleJob();

    doDelete("/api/v1/test-module/test-scheduler/sample-job");
  }

  /**
   * Sample job status successful test.
   */
  @Test
  public void statusSampleJobTest() throws Exception {
    when(testSchedulerService.getSampleJobStatus()).thenReturn("Sample job not scheduled");

    MockHttpServletResponse response = doGet("/api/v1/test-module/test-scheduler/sample-job");
    KameHouseGenericResponse responseBody = getResponseBody(response,
        KameHouseGenericResponse.class);

    verifyResponseStatus(response, HttpStatus.OK);
    assertEquals("Sample job not scheduled", responseBody.getMessage());
    verify(testSchedulerService, times(1)).getSampleJobStatus();
  }
}
