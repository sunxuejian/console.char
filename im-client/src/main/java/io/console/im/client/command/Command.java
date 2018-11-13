package io.console.im.client.command;

import org.apache.commons.cli.Options;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuejian.sun
 * @date 2018/11/13 18:55
 */
public interface Command {

    Map<String, Options> commands = new HashMap<>(64);

    /**
     * 指令
     * @return command
     */
    String command();

    /**
     * 组装command 选项
     * @return options
     */
    Options assembly();

    /**
     * 收集commands
     */
    default void cacheCommands(){
        commands.put(command(),assembly());
    }
}
