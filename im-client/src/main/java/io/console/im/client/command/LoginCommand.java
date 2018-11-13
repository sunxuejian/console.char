package io.console.im.client.command;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author xuejian.sun
 * @date 2018/11/13 18:54
 */
public class LoginCommand extends CommandManager{

    @Override
    public String command() {
        return "login";
    }

    @Override
    public Options assembly() {
        Options options = new Options();
        Option user = Option.builder("u")
                .longOpt("user")
                .hasArg()
                .argName("username")
                .valueSeparator()
                .desc("用户名").build();
        options.addOption(user);

        Option pwd = Option.builder("p")
                .longOpt("pwd")
                .hasArgs()
                .argName("password")
                .valueSeparator()
                .desc("密码").build();
        options.addOption(pwd);
        return options;
    }
}
