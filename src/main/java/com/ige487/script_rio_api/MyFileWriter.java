package com.ige487.script_rio_api;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MyFileWriter {
    private String filePath;
    private String folderResult = "/home/abeauquel/projet/script_riotapi/scripts/";

    public MyFileWriter(String fileName) {
        this.filePath = folderResult + fileName;
    }

    public void writeListStringInFile(List<String> list){
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            for (String line:list) {
                writer.write(line + "\n");
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
