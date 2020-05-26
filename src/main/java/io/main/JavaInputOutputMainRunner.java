package io.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JavaInputOutputMainRunner {
  public static void main(String[] args) {
    File location = new File(args[0]);
    if (location.isDirectory()) {
      File newTextFile = new File(location.getAbsolutePath() + "/FilesAndFolders.txt");
      if (!newTextFile.exists()) {
        try {
          newTextFile.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(newTextFile))) {
        for (String string : Directory.directoryStructureToList(location, 0)) {
          bufferedWriter.write(string);
          bufferedWriter.newLine();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (location.isFile() && location.getName().contains(".txt")) {
      System.out.println(
          "Folders in the directory - " + Directory.countFolders(location));
      System.out.println(
          "Files in the directory - " + Directory.countFiles(location));
      System.out.println(
          "Average file name length is - " + Directory.countAverageFileNameLength(location));
      System.out.println(
          "Average files per folder - " + Directory.countAverageFilesPerFolder(location));
    } else if (location.isFile()) {
      System.out.println("Only .txt files are allowed");
    } else {
      System.out.println("Path not found");
    }
  }
}
