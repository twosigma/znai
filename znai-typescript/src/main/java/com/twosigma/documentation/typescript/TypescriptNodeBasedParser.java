package com.twosigma.documentation.typescript;

import com.twosigma.utils.JsonUtils;
import com.twosigma.utils.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TypescriptNodeBasedParser {
    private final Process node;
    private final OutputStream outputStream;
    private final InputStream inputStream;

    public TypescriptNodeBasedParser() {
        node = createProcess(tempParserCodeLocation());

        inputStream = node.getInputStream();
        outputStream = node.getOutputStream();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, ?>> parsedFile(Path filePath) {
        write(filePath.toAbsolutePath().toString());
        String response = readResponse();

        return (List<Map<String, ?>>) JsonUtils.deserializeAsList(response);
    }

    private Path tempParserCodeLocation() {
        String textContent = ResourceUtils.textContent("typeScriptParserBundle.js");

        try {
            Path path = Files.createTempFile("typescriptParser", ".js");
            path.toFile().deleteOnExit();

            Files.write(path, textContent.getBytes());

            return path;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(String content) {
        try {
            outputStream.write((content + "\n").getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readResponse() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }

    private Process createProcess(Path scriptPath) {
        try {
            String nodePath = getNodePath();
            return new ProcessBuilder().command(nodePath, scriptPath.toAbsolutePath().toString()).redirectErrorStream(true).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNodePath() {
        String nodeBin = System.getProperty("node.bin");
        return nodeBin != null ? nodeBin : "node";
    }

    public static void main(String[] args) {
        TypescriptNodeBasedParser parser = new TypescriptNodeBasedParser();
    }
}