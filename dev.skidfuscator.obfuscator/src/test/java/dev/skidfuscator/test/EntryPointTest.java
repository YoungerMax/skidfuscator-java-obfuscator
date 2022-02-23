package dev.skidfuscator.test;

import dev.skidfuscator.obf.Bootstrapper;
import org.junit.Test;
import org.mapleir.Boot;
import org.mapleir.Main;

/**
 * @author Ghast
 * @since 06/03/2021
 * SkidfuscatorV2 © 2021
 */

public class EntryPointTest {

    @Test
    public void test2() throws Exception {
        Bootstrapper.main(new String[] {
                "--input-file", "src/test/resources/test.jar",
                "--output-file", "output.jar"
        });
    }

    @Test
    public void oldCliTest() {
        Bootstrapper.main(new String[] {
                "src/test/resources/test.jar"
        });
    }
}
