package dev.skidfuscator.obf;

import dev.skidfuscator.obf.command.ObfuscateCommand;
import picocli.CommandLine;

import java.io.File;

/**
 * @author Ghast
 * @since 21/01/2021
 * SkidfuscatorV2 © 2021
 */
public class Bootstrapper {
    public static void main(String[] args) {
        // pre-cli check
        if (new File(args[0]).exists()) {
            // don't use the cli
            ObfuscateCommand obfuscateCommand = new ObfuscateCommand();
            obfuscateCommand.inputFile = new File(args[0]);
            obfuscateCommand.call();
        } else {
            // use the cli
            new CommandLine(new ObfuscateCommand()).execute(args);
        }
    }
}
