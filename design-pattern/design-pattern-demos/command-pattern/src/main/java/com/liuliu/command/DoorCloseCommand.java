package com.liuliu.command;

import com.liuliu.dynamic.pojo.Door;

/**
 * @Author: liulei
 * @Time: 2021/3/4 15:07
 * @Description
 */

public class DoorCloseCommand implements Command {

    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }
}
