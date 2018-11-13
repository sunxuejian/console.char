package io.console.im.client.command;

/**
 * @author xuejian.sun
 * @date 2018/11/13 19:17
 */
public class CommandInstance {

    private LoginCommand loginCommand;

    public CommandInstance() {
        initBean();
    }

    private void initBean(){
        loginCommand = new LoginCommand();
    }
}
