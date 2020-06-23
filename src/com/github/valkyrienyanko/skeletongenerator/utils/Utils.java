package com.github.valkyrienyanko.skeletongenerator.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static void createDirectory(String path) {
        File file = new File("dist/" + path);
        if (file.mkdir()) {
            // Created directory
        }
    }

    public static void writeFile(String name, List<String> data) {
        try {
            FileWriter writer = new FileWriter("dist/" + name);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (String line : data) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createPomXML(String groupId, String artifactId) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/pom.xml");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("<groupId>")) {
                    line = String.format(line, groupId);
                }

                if (line.contains("<artifactId>")) {
                    line = String.format(line, artifactId);
                }

                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/pom.xml", data);
    }

    public static void createPluginYML(String artifactId) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/plugin.yml");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("name:")) {
                    line = String.format(line, artifactId);
                }

                if (line.contains("main:")) {
                    line = String.format(line, artifactId.toLowerCase(), artifactId);
                }
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/src/main/resources/plugin.yml", data);
    }

    public static void createTemplateJava(String groupId, String artifactId) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/Template.java");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("package")) {
                    line = String.format(line, artifactId.toLowerCase());
                }

                if (line.contains("class")) {
                    line = String.format(line, artifactId);
                }
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/src/main/java/" + groupId.replaceAll("\\.", "/") + "/" + artifactId.toLowerCase() + "/" + artifactId + ".java", data);
    }

    public static void createTemplateIML(String artifactId) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/Template.iml");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/" + artifactId + ".iml", data);
    }

    public static void createReadme(String artifactId, String description, String discord) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/README.md");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("<!--Title-->")) {
                    line = String.format(line, artifactId);
                }

                if (line.contains("<!--Description-->")) {
                    line = String.format(line, description);
                }

                if (line.contains("<!--Discord-->")) {
                    line = String.format(line, discord);
                }

                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/.github/README.md", data);
    }

    public static void createContributing(String artifactId) {
        List<String> data = new ArrayList<String>();

        File file = new File("template/CONTRIBUTING.md");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                data.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writeFile(artifactId + "/.github/CONTRIBUTING.md", data);
    }
}
