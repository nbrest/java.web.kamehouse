package com.nicobrest.kamehouse.testmodule.model;

import com.nicobrest.kamehouse.commons.dao.Identifiable;
import com.nicobrest.kamehouse.commons.utils.JsonUtils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DragonBallUser used for the test endpoints.
 * 
 * @author nbrest
 */
@Entity
@Table(name = "dragonball_user")
public class DragonBallUser implements Identifiable, Serializable {

  private static final long serialVersionUID = 159367676076449689L;

  @Id
  @Column(name = "id", unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "age")
  private int age;

  @Column(name = "power_level")
  private int powerLevel;

  @Column(name = "stamina")
  private int stamina;

  public DragonBallUser() {
  }

  /**
   * Constructor.
   */
  public DragonBallUser(Long id, String username, String email, int age, int powerLevel,
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

  /**
   * Attacks another DragonBallUser.
   */
  public void attack(DragonBallUser opponent) {
    /*
     * Check for nulls in parameters in methods that can be called from outside
     * the application, where I don´t know what the client can send. In this
     * case, it should be safe to assume DragonBallUser opponent will not be
     * null, so I can skip that check. If I end up having a NullPointerException
     * here, check if I need to do the null check here or if there's a bug
     * somewhere that allows this code to receive a null value.
     */
    int currentOpponentStamina = opponent.getStamina();
    currentOpponentStamina = currentOpponentStamina - powerLevel;
    if (currentOpponentStamina < 0) {
      currentOpponentStamina = 0;
    }
    opponent.setStamina(currentOpponentStamina);
  }

  /**
   * Recovers stamina.
   */
  public void recoverStamina() {
    stamina = stamina + powerLevel;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(id).append(username).toHashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof DragonBallUser) {
      final DragonBallUser other = (DragonBallUser) obj;
      return new EqualsBuilder().append(id, other.getId()).append(username, other.getUsername())
          .isEquals();
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return JsonUtils.toJsonString(this, super.toString());
  }
}