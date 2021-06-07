package com.liuliu;

import com.liuliu.command.*;
import com.liuliu.controller.RemoteController;
import com.liuliu.dynamic.pojo.Door;
import com.liuliu.dynamic.pojo.Light;

/**
 * @Author: liulei
 * @Time: 2021/3/4 14:34
 * @Description
 */

public class CommandTest {

    public static void main(String[] args) {
        Light bedroom_light = new Light("bedroom light");
        Door bedroom_door = new Door("bedroom door");
        Command lightOnCommand = new LightOnCommand(bedroom_light);
        Command lightOffCommand = new LightOffCommand(bedroom_light);
        Command doorOpenCommand = new DoorOpenCommand(bedroom_door);
        Command doorCloseCommand = new DoorCloseCommand(bedroom_door);
        RemoteController controller = new RemoteController(lightOnCommand, lightOffCommand);
        controller.pushOnButton();
        controller.pushOffButton();
        controller.setOnCommand(doorOpenCommand);
        controller.setOffCommand(doorCloseCommand);
        controller.pushOnButton();
        controller.pushOffButton();
    }
}
