package com.liuliu.controller;

import com.liuliu.command.Command;

/**
 * @Author: liulei
 * @Time: 2021/3/4 15:09
 * @Description
 */

public class RemoteController implements Controller{
    private Command onCommand;
    private Command offCommand;

    public void setOnCommand(Command onCommand) {
        this.onCommand = onCommand;
    }

    public void setOffCommand(Command offCommand) {
        this.offCommand = offCommand;
    }

    public RemoteController(Command onCommand, Command offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void pushOnButton(){
        onCommand.execute();
    }

    public void pushOffButton(){
        offCommand.execute();
    }
}
