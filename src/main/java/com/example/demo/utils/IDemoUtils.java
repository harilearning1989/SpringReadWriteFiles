package com.example.demo.utils;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

public interface IDemoUtils {

    public static String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
    }
}
