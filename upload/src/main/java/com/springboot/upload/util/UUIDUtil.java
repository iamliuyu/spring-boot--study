package com.springboot.upload.util;

import java.io.File;
import java.util.UUID;

public class UUIDUtil {
    public static String getUUID32() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }
}
