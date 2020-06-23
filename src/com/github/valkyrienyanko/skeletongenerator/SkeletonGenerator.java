package com.github.valkyrienyanko.skeletongenerator;

import com.github.valkyrienyanko.skeletongenerator.utils.Utils;

import java.util.Scanner;

public class SkeletonGenerator {
    public static void main(String[] args) {
        // Inputs
        String artifactId;
        String groupId;
        String description;
        String discord;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'artifactId'");
        String scannedArtifactId = scanner.nextLine();
        artifactId = scannedArtifactId.equals("") ? "Template": scannedArtifactId;

        System.out.println("Enter 'groupId'");
        String scannedGroupId = scanner.nextLine();
        groupId = scannedGroupId.equals("") ? "com.github.valkyrienyanko" : scannedGroupId;

        System.out.println("Enter 'description'");
        String scannedDescription = scanner.nextLine();
        description = scannedDescription.equals("") ? "A useful plugin.." : scannedDescription;

        System.out.println("Enter 'discord'");
        String scannedDiscord = scanner.nextLine();
        discord = scannedDiscord.equals("") ? "LinkNotSpecified" : scannedDiscord;

        String[] groupIdArr = groupId.split("\\.");

        // Generate plugin skeleton

        // root
        Utils.createDirectory(""); // create 'dist/'
        Utils.createDirectory(artifactId);
        Utils.createDirectory(artifactId + "/.github");
        Utils.createPomXML(groupId, artifactId);
        Utils.createTemplateIML(artifactId);

        // git
        Utils.createReadme(artifactId, description, discord);
        Utils.createContributing(artifactId);

        // src
        Utils.createDirectory(artifactId + "/src");
        Utils.createDirectory(artifactId + "/src/main");
        Utils.createDirectory(artifactId + "/src/main/java");
        Utils.createDirectory(artifactId + "/src/main/java/" + groupIdArr[0]);
        Utils.createDirectory(artifactId + "/src/main/java/" + groupIdArr[0] + "/" + groupIdArr[1]);
        Utils.createDirectory(artifactId + "/src/main/java/" + groupIdArr[0] + "/" + groupIdArr[1] + "/" + groupIdArr[2]);
        Utils.createDirectory(artifactId + "/src/main/java/" + groupIdArr[0] + "/" + groupIdArr[1] + "/" + groupIdArr[2] + "/" + artifactId.toLowerCase());
        Utils.createTemplateJava(groupId, artifactId);

        // Resources
        Utils.createDirectory(artifactId + "/src/main/resources");
        Utils.createPluginYML(artifactId);

        System.out.println("Done!");
    }
}
