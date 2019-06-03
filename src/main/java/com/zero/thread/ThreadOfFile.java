package com.zero.thread;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadOfFile {



    public static List<String> getAllFile(String path) throws IOException {
        List allFile = new ArrayList();
        Stream<String> stringStream = Files.lines(Paths.get(path), StandardCharsets.UTF_8);
        List<String> collect = stringStream.collect(Collectors.toList());
        File f = new File(path);
        File[] files = f.listFiles();
        for (File file : files) {
            if (file.isDirectory()){
                String directoryPath = file.getPath();
                getAllFile(directoryPath);
            }else {
                String filePath = file.getPath();
                if (!filePath.endsWith(".md")){
                    continue;
                }
                allFile.add(filePath) ;
            }
        }
        return allFile ;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getAllFile("/Users/mac/Downloads/百度云盘/"));
    }
}

