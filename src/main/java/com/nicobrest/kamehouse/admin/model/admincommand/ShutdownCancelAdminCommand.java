package com.nicobrest.kamehouse.admin.model.admincommand;

import com.nicobrest.kamehouse.admin.model.systemcommand.ShutdownCancelSystemCommand;

/**
 * AdminCommand to cancel a scheduled shutdown of the server.
 * 
 * @author nbrest
 *
 */
public class ShutdownCancelAdminCommand extends AdminCommand {

  /**
   * Set the required SystemCommands to achieve this AdminCommand.
   */
  public ShutdownCancelAdminCommand() {
    systemCommands.add(new ShutdownCancelSystemCommand());
  }
}