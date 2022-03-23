package dev.skidfuscator.obf.command;

import dev.skidfuscator.obf.Skidfuscator;
import dev.skidfuscator.obf.transform.impl.kappa.AhegaoPass;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.Callable;

/**
 * @author Ghast
 * @since 06/03/2021
 * SkidfuscatorV2 © 2021
 */

@CommandLine.Command(name = "obfuscate", mixinStandardHelpOptions = true, version = "Obfuscate 1.0.8",
    description = "Obfuscate a jar file")
public class ObfuscateCommand implements Callable<Integer> {
    @CommandLine.Option(names = {"--input-file", "--inputFile", "--input", "-i"}, description = "Input file", type = File.class, required = true)
    public File inputFile;

    @CommandLine.Option(names = {"--output-file", "--outputFile", "--output", "-o"}, description = "Output file", type = File.class)
    public File outputFile;

    @CommandLine.Option(names = {"--antidump"}, description = "Prevents dump", defaultValue = "false", type = Boolean.class)
    public boolean preventDump = false;

    @CommandLine.Option(names = {"--obfuscate-names-for-injected-content"}, description = "Obfuscates string encryption method names and ahegao constants", defaultValue = "false", type = Boolean.class)
    public boolean obfInjected = false;

    @CommandLine.Option(names = {"--obfuscate-names-dictionary"}, description = "Dictionary for names of injected content (you must enable --obfuscate-names-for-injected-content first)", defaultValue = "Oo", type = String.class)
    public String obfDictionary = "Oo";

    @CommandLine.Option(names = {"--obfuscate-names-length"}, description = "Length of the names of injected content (you must enable --obfuscate-names-for-injected-content first)", defaultValue = "12", type = Integer.class)
    public int obfLength = 12;

    @Override
    public Integer call() {
        try {
            validate();
            new Skidfuscator(this).init();

            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void validate() throws IOException {
        // input file
        if (!inputFile.exists()) {
            throw new IOException("input file is nonexistent");
        }

        // output file
        if (outputFile == null) {
            outputFile = new File(inputFile.getPath() + "-out.jar");
        } else {
            outputFile = new File(outputFile.getAbsolutePath());
        }

        if (!outputFile.getParentFile().isDirectory()) {
            Files.createDirectories(outputFile.toPath());
        }
    }
}
