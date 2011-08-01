package org.apache.uima.tm.textruler.tools;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.util.FileUtils;

public class TestSetGenerator {

  public static String trainingDir = "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/ALL/training/";

  public static String testDir = "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/ALL/withoutslots/";

  public static String trainingFilesPrefix = "postagged_";

  public static String testFilesPrefix = "postagged_withoutslots_";

  public static class PairEntry {
    public String trainingsFileName;

    public String testFileName;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {

    File trainingFolder = new File(trainingDir);
    File[] files = trainingFolder.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });

    List<PairEntry> pairs = new ArrayList<PairEntry>();

    for (File file : files) {
      String fName = file.getName();
      String docName = fName.substring(trainingFilesPrefix.length());
      PairEntry p = new PairEntry();
      p.trainingsFileName = fName;
      p.testFileName = testFilesPrefix + docName;
      pairs.add(p);
    }

    try {
      // makeXFoldCrossValidationSets(10, pairs,
      // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/test2_out/");
      // makeXFoldCrossValidationSets(5,
      // pairs,
      // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/Subset100/5fold");

      // List<PairEntry> list1 = new ArrayList<PairEntry>();
      // List<PairEntry> list2 = new ArrayList<PairEntry>();
      // for (int i = 0; i<pairs.size(); i++)
      // {
      // if (i % 2 == 0)
      // list1.add(pairs.get(i));
      // else
      // list2.add(pairs.get(i));
      // }
      // copyPairs(list1,
      // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/halfhalf/0/training");
      // copyPairs(list2,
      // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/halfhalf/0/testing");

      Random rand = new Random();
      List<PairEntry> list1 = new ArrayList<PairEntry>(pairs);
      List<PairEntry> list2 = new ArrayList<PairEntry>();
      for (int i = 0; i < 28; i++) {
        int index = rand.nextInt(list1.size());
        list2.add(list1.get(index));
        list1.remove(index);
      }
      while (list1.size() > 252) {
        int index = rand.nextInt(list1.size());
        list1.remove(index);
      }
      copyPairs(list1,
              "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/9010_middle/0/training");
      copyPairs(list2,
              "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/9010_middle/0/testing");

      // double step = 485.0 / 97.0;
      // List<PairEntry> list = new ArrayList<PairEntry>();
      // double i = 0;
      // while (list.size() != 97)
      // {
      // int intI = (int)Math.round(i);
      // if (intI >= 485) intI -= 484;
      // TextRulerToolkit.log(""+intI);
      // list.add(pairs.get(intI));
      // i += step;
      // }
      // copyPairs(list,
      // "/Users/tobi/Documents/UniLaptop/Diplomarbeit/TestDataSets/withPosTags/Subset100");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void copyPairs(List<PairEntry> pairs, String dest) throws IOException {
    dest = TextRulerToolkit.addTrailingSlashToPath(dest);
    String withTags = dest + "withtags";
    String withoutTags = dest + "withouttags";
    new File(withTags).mkdir();
    new File(withoutTags).mkdir();

    for (PairEntry pe : pairs) {
      File srcFile = new File(trainingDir + pe.trainingsFileName);
      File dstFile = new File(withTags);
      FileUtils.copyFile(srcFile, dstFile);
      srcFile = new File(testDir + pe.testFileName);
      dstFile = new File(withoutTags);
      FileUtils.copyFile(srcFile, dstFile);
    }

  }

  public static void makeXFoldCrossValidationSets(int partitionCount, List<PairEntry> pairs,
          String destinationPath) throws IOException {
    List<PairEntry> pairsCopy = new ArrayList<PairEntry>(pairs);
    int partSize = (int) Math.ceil((double) pairsCopy.size() / ((double) partitionCount));

    Random rand = new Random();

    ArrayList<ArrayList<PairEntry>> partition = new ArrayList<ArrayList<PairEntry>>();

    for (int partNo = 0; partNo < partitionCount; partNo++) {
      ArrayList<PairEntry> part = new ArrayList<PairEntry>();
      partition.add(part);
      if (partNo == partitionCount - 1) {
        part.addAll(pairsCopy); // the last part gets the remaining
        // items...
      } else {
        for (int i = 0; i < partSize; i++) {
          int index = rand.nextInt(pairsCopy.size());
          part.add(pairsCopy.get(index));
          pairsCopy.remove(index);
        }
      }
    }
    destinationPath = TextRulerToolkit.addTrailingSlashToPath(destinationPath);
    for (int i = 0; i < partitionCount; i++) {
      String foldDest = destinationPath + i + "/";
      TextRulerToolkit.log("MK: " + new File(foldDest).mkdir());
      new File(foldDest + "training").mkdir();
      new File(foldDest + "testing").mkdir();
      new File(foldDest + "training/withtags").mkdir();
      new File(foldDest + "training/withouttags").mkdir();
      new File(foldDest + "testing/withtags").mkdir();
      new File(foldDest + "testing/withouttags").mkdir();
      String trainingWithTagsDir = foldDest + "training/withtags/";
      String trainingWithoutTagsDir = foldDest + "training/withouttags/";
      String testingWithTagsDir = foldDest + "testing/withtags/";
      String testingWithoutTagsDir = foldDest + "testing/withouttags/";

      for (PairEntry pe : partition.get(i)) {
        // part no i is the testing fold
        File srcFile = new File(trainingDir + pe.trainingsFileName);
        File dstFile = new File(testingWithTagsDir);
        FileUtils.copyFile(srcFile, dstFile);
        srcFile = new File(testDir + pe.testFileName);
        dstFile = new File(testingWithoutTagsDir);
        FileUtils.copyFile(srcFile, dstFile);
      }
      for (int restI = 0; restI < partitionCount; restI++)
        if (restI != i) {
          for (PairEntry pe : partition.get(restI)) {
            File srcFile = new File(trainingDir + pe.trainingsFileName);
            File dstFile = new File(trainingWithTagsDir);
            FileUtils.copyFile(srcFile, dstFile);
            srcFile = new File(testDir + pe.testFileName);
            dstFile = new File(trainingWithoutTagsDir);
            FileUtils.copyFile(srcFile, dstFile);
          }
        }
    }
  }

}
