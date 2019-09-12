package com.nicobrest.kamehouse.testmodule.service.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * DragonBallUser DTO used for the test endpoints.
 * 
 * @author nbrest
 */
public class DragonBallUserDto implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(DragonBallUserDto.class);
  private static final long serialVersionUID = 159367676076449689L;

  private Long id;
  private String username;
  private String email;
  private int age;
  private int powerLevel;
  private int stamina;

  public DragonBallUserDto() {
  }

  /** 
   * Constructor.
   */
  public DragonBallUserDto(Long id, String username, String email, int age, int powerLevel,
      int stamina) {

    this.id = id;
    this.username = username;
    this.email = email;
    this.age = age;
    this.powerLevel = powerLevel;
    this.stamina = stamina;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setPowerLevel(int powerLevel) {
    this.powerLevel = powerLevel;
  }

  public void setStamina(int stamina) {
    this.stamina = stamina;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public int getAge() {
    return age;
  }

  public int getPowerLevel() {
    return powerLevel;
  }

  public int getStamina() {
    return stamina;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(id).append(username).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof DragonBallUserDto) {
      final DragonBallUserDto other = (DragonBallUserDto) obj;
      return new EqualsBuilder().append(id, other.getId()).append(username, other.getUsername())
          .isEquals();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {

    try {
      return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
      logger.error("Error formatting json", e);
    }
    return "DragonBallUser: INVALID_STATE";
  }
}