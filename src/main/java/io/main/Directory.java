package io.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Directory {
  public static List<String> directoryStructureToList(File location, int subfolderDepth) {
    List<String> filesAsLines = new ArrayList<>();
    List<String> secondaryLines = new ArrayList<>();
    String line = "";
    if (subfolderDepth > 0) {
      line += String.join("", Collections.nCopies(subfolderDepth - 1, "    "));
      filesAsLines.add(line + "|---" + location.getName());
    } else {
      filesAsLines.add(line + location.getName());
    }
    for (File file : location.listFiles()) {
      if (file.isFile()) {
        filesAsLines.add(line + "        " + file.getName());
      } else if (file.isDirectory()) {
        secondaryLines.addAll(directoryStructureToList(file.getAbsoluteFile(), subfolderDepth + 1));
      }
    }
    filesAsLines.addAll(secondaryLines);
    return filesAsLines;
  }

  public static int countFolders(File file) {
    int numberOfFolders = 0;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      numberOfFolders =
          (int) bufferedReader.lines().filter(o -> o.trim().startsWith("|---")).count();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return numberOfFolders;
  }

  public static int countFiles(File file) {
    int numberOfFiles = 0;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      numberOfFiles =
          (int) bufferedReader.lines()
              .filter(o -> o.startsWith(" ") && !o.trim().isEmpty() && !o.trim().startsWith("|---"))
              .count();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return numberOfFiles;
  }

  public static double countAverageFileNameLength(File file) {
    double averageLength = 0;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      averageLength = bufferedReader.lines()
          .filter(o -> o.startsWith(" ") && !o.trim().isEmpty() && !o.trim().startsWith("|---"))
          .mapToInt(o -> o.trim().length())
          .average()
          .orElse(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return averageLength;
  }

  public static double countAverageFilesPerFolder(File file) {
    return (double) countFiles(file) / countFolders(file);
  }
}
