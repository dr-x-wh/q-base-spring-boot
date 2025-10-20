package com.drx.core.tools;

import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UUIDTool {

    public static String generator() {
        return Generators.timeBasedEpochGenerator().generate().toString();
    }

    public static UUID generatorUUID() {
        return Generators.timeBasedEpochGenerator().generate();
    }

}
