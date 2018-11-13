package io.console.im.client.command;


/**
 * @author xuejian.sun
 * @date 2018/11/13 14:36
 */
public abstract class CommandManager implements Command {

    public CommandManager() {
        cacheCommands();
    }
}
