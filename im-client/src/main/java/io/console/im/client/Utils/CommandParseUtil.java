package io.console.im.client.Utils;

import org.apache.commons.cli.*;

/**
 * @author xuejian.sun
 * @date 2018/11/13 17:53
 */
public final class CommandParseUtil {

    private CommandParseUtil(){}

    private static final CommandLineParser parser = new DefaultParser();

    public static CommandLine parse(Options options, String[] args) throws ParseException {
        return parser.parse(options,args);
    }
}
