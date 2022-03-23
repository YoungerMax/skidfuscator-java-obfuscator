package dev.skidfuscator.obf.utils;

import dev.skidfuscator.obf.command.ObfuscateCommand;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class MiscUtils {
    public static final List<String> generatedStrings = new ArrayList<>();

    public <T> int indexOf(T[] arr, T t) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == t) return i;
        }

        return -1;
    }

    public String getObfuscatedString(ObfuscateCommand command, String defaultName) {
        return command.obfInjected ? getObfuscatedString(command.obfDictionary, command.obfLength) : defaultName;
    }

    public String getObfuscatedString(String dictionary, int length) {
        String s = "";

        for (int i = 0; length > i; i++) {
            s += dictionary.charAt(RandomUtil.nextInt(dictionary.length()));
        }

        if (generatedStrings.contains(s)) {
            return getObfuscatedString(dictionary, length);
        } else {
            generatedStrings.add(s);
        }

        return s;
    }
}
