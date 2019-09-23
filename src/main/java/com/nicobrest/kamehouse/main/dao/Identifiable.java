package com.nicobrest.kamehouse.main.dao;

/**
 * Interface for persistable model objects that need to be able to be
 * identified.
 * 
 * @author nicolas.brest
 *
 */
public interface Identifiable {

  /**
   * Get the id of the entity.
   */
  public Long getId();
}