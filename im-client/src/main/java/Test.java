import io.console.im.client.Utils.CommandParseUtil;
import io.console.im.client.command.Command;
import io.console.im.client.command.CommandInstance;
import org.apache.commons.cli.*;

import java.util.Scanner;

/**
 * @author xuejian.sun
 * @date 2018/11/13 13:54
 */
public class Test {

    public static void main(String[] args) throws ParseException {
        CommandInstance instance = new CommandInstance();
        simpleTest(args);
    }

    public static void simpleTest(String[] args) throws ParseException {
        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输出指令:");
        while(true){
            if(scanner.hasNext()){
                String next = scanner.nextLine().trim();
                String[] cod = next.split(" ");
                Options options = Command.commands.get(cod[0]);
                if(cod.length == 1){
                    if(options != null){
                        hf.printHelp(cod[0],options);
                    }
                }else {
                    CommandLine parse = CommandParseUtil.parse(options, cod);
                    Option option = options.getOption(cod[1]);
                    System.out.println(option.getValue());
                }
            }
        }
    }
}
