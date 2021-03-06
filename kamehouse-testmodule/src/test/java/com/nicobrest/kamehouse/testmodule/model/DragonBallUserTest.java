package com.nicobrest.kamehouse.testmodule.model;

import static org.junit.Assert.assertEquals;

import com.nicobrest.kamehouse.commons.testutils.TestUtils;
import com.nicobrest.kamehouse.testmodule.model.dto.DragonBallUserDto;
import com.nicobrest.kamehouse.testmodule.testutils.DragonBallUserTestUtils;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the DragonBallUser class.
 * 
 * @author nbrest
 */
public class DragonBallUserTest {

  private TestUtils<DragonBallUser, DragonBallUserDto> testUtils; 
  
  /**
   * Clears data from the repository before each test.
   */
  @Before
  public void setUp() {
    testUtils = new DragonBallUserTestUtils();
    testUtils.initTestData();
  }
  
  /**
   * Tests attack and recover.
   */
  @Test
  public void attackAndRecoverTest() {
    DragonBallUser goku = testUtils.getTestDataList().get(0);
    DragonBallUser gohan = testUtils.getTestDataList().get(1);
    assertEquals(1000, goku.getStamina());
    assertEquals(1000, gohan.getStamina());

    goku.attack(gohan);
    assertEquals(970, gohan.getStamina());
    gohan.recoverStamina();
    assertEquals(990, gohan.getStamina());

    for (int i = 0; i < 100; i++) {
      goku.attack(gohan);
    }
    assertEquals(0, gohan.getStamina());
  }
}
