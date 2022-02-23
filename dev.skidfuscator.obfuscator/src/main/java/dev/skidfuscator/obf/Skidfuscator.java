package dev.skidfuscator.obf;

import dev.skidfuscator.obf.command.ObfuscateCommand;
import dev.skidfuscator.obf.directory.SkiddedDirectory;
import dev.skidfuscator.obf.init.DefaultInitHandler;
import dev.skidfuscator.obf.init.SkidSession;
import dev.skidfuscator.obf.utils.MapleJarUtil;
import org.mapleir.deob.PassGroup;
import org.mapleir.ir.code.expr.InstanceofExpr;

import java.io.File;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Ghast
 * @since 21/01/2021
 * SkidfuscatorV2 © 2021
 */
public class Skidfuscator {
    public static Skidfuscator INSTANCE;

    public final ObfuscateCommand obfuscateCommand;

    public Skidfuscator(ObfuscateCommand obfuscateCommand) {
        INSTANCE = this;
        this.obfuscateCommand = obfuscateCommand;
    }

    public void init() {
        final String[] logo = new String[] {
                "",
                "  /$$$$$$  /$$       /$$       /$$  /$$$$$$                                           /$$",
                " /$$__  $$| $$      |__/      | $$ /$$__  $$                                         | $$",
                "| $$  \\__/| $$   /$$ /$$  /$$$$$$$| $$  \\__//$$   /$$  /$$$$$$$  /$$$$$$$  /$$$$$$  /$$$$$$    /$$$$$$   /$$$$$$",
                "|  $$$$$$ | $$  /$$/| $$ /$$__  $$| $$$$   | $$  | $$ /$$_____/ /$$_____/ |____  $$|_  $$_/   /$$__  $$ /$$__  $$",
                " \\____  $$| $$$$$$/ | $$| $$  | $$| $$_/   | $$  | $$|  $$$$$$ | $$        /$$$$$$$  | $$    | $$  \\ $$| $$  \\__/",
                " /$$  \\ $$| $$_  $$ | $$| $$  | $$| $$     | $$  | $$ \\____  $$| $$       /$$__  $$  | $$ /$$| $$  | $$| $$",
                "|  $$$$$$/| $$ \\  $$| $$|  $$$$$$$| $$     |  $$$$$$/ /$$$$$$$/|  $$$$$$$|  $$$$$$$  |  $$$$/|  $$$$$$/| $$",
                " \\______/ |__/  \\__/|__/ \\_______/|__/      \\______/ |_______/  \\_______/ \\_______/   \\___/   \\______/ |__/",
                "",
                "                       Author: Ghast     Version: 1.0.8     Today: " + new Date(Instant.now().toEpochMilli()).toGMTString(),
                "",
                ""
        };

        for (String s : logo) {
            System.out.println(s);
        }


        start(this.obfuscateCommand);
    }

    public static File start(ObfuscateCommand obfuscateCommand) {
        final SkiddedDirectory directory = new SkiddedDirectory(null);
        directory.init();

        final SkidSession session = new DefaultInitHandler().init(obfuscateCommand.inputFile, obfuscateCommand.outputFile);
        try {
            MapleJarUtil.dumpJar(session.getClassSource(), session.getJarDownloader(), new PassGroup("Output"),
                    session.getOutputFile().getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obfuscateCommand.outputFile;
    }

    // for discord bot
    public static File start(File inputFile) {
        ObfuscateCommand command = new ObfuscateCommand();
        command.inputFile = inputFile;
        command.call();

        return command.outputFile;
    }
}
