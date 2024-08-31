package com.codegen.springcodegen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service  // Marks this class as a Spring service component , making it available for dependency injection


public class GeneratedCodeService {
    // Injects the value from application properties for the path for the path where generated files will be stored
    // Default value is set to "src/main/resources/generated" if not provided
    @Value("${generated.files.path:src/main/resources/generated}")
    private String generatedFilesPath;

    /**
     * Writes the given source code to a file.
     * The file is named after the class name extracted from the source code.
     * If the class name is not found, an exception is thrown.
     *
     * @param sourceCode The source code to be written to a file
     */

    public void writeToFile(String sourceCode) {
        // Extracts the class name from the source code
        String className = extractClassName(sourceCode);
        if (className == null) {
            throw new IllegalArgumentException("Source Code Does Not Contain a Class Name");
        }
        // Constructs the file path using the extracted class name
        Path filePath = Paths.get(generatedFilesPath, className + ".java");

        try {
            // Creates the directory structure if it doesn't exist
            Files.createDirectories(filePath.getParent());
            // Writes the source code to the specified file
            Files.writeString(filePath, sourceCode);
            System.out.printf("Class '%s' has been written to %s%n", className, filePath);
        } catch (IOException e) {
            System.err.printf("Error writing to file: %s%n", e.getMessage());
        }
    }

    /**
     * Overloaded method to write multiple source codes to files.
     * Each source code in the array is processed by the single-file method.
     *
     * @param sourceCodes An array of source codes to be written to files
     */
    public void writeToFile(String[] sourceCodes) {
        Arrays.stream(sourceCodes).forEach(this::writeToFile);
    }

    /**
     * Extracts the class name from the given source code.
     * Assumes the class name follows the keyword "class" and is a single word.
     *
     * @param sourceCode The source code from which to extract the class name
     * @return The extracted class name, or null if no class name is found
     */

    private String extractClassName(String sourceCode) {
        // Regular expression pattern to match the class name following the "class" keyword
        Pattern pattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(sourceCode);
        if (matcher.find()) {
            // Returns the first match, which should be the class name
            return matcher.group(1);
        }
        return null; // Returns null if no class name is found
    }
}