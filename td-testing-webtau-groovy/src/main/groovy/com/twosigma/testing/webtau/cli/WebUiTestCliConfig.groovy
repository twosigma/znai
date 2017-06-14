package com.twosigma.testing.webtau.cli

import com.twosigma.console.ConsoleOutputs
import com.twosigma.console.ansi.Color
import com.twosigma.testing.webtau.cfg.WebTauConfig
import com.twosigma.utils.FileUtils
import org.apache.commons.cli.*

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author mykola
 */
class WebUiTestCliConfig {
    private WebTauConfig cfg = WebTauConfig.INSTANCE

    private List<String> testFiles
    private String env
    private Path configFile
    private CommandLine commandLine
    private ConfigObject configObject

    WebUiTestCliConfig(String... args) {
        parseArgs(args)
        parseConfig()

        if (configObject) {
            cfg.acceptConfigValues("config file", configObject.flatten())
        }

        cfg.acceptConfigValues("command line argument", commandLineArgsAsMap())
    }

    List<String> getTestFiles() {
        return testFiles
    }

    void print() {
        cfg.print()
    }

    private void parseArgs(String[] args) {
        Options options = createOptions()
        commandLine = createCommandLine(args, options)

        if (commandLine.hasOption("help") || args.length < 1) {
            HelpFormatter helpFormatter = new HelpFormatter()
            helpFormatter.printHelp("webtau [options] [testFile1] [testFile2]", options)
            System.exit(1)
        }

        testFiles = new ArrayList<>(commandLine.argList)
        Path workingDir = Paths.get(cliValue(cfg.getWorkingDirConfigName(), ""))
        configFile = workingDir.resolve(cliValue("config", "test.cfg"))
        env = Paths.get(cliValue("env", "local"))
    }

    private void parseConfig() {
        ConfigSlurper configSlurper = new ConfigSlurper(env)
        if (! Files.exists(configFile)) {
            ConsoleOutputs.out("skipping config file as it is not found: ", Color.CYAN, configFile)
            return
        }

        configObject = configSlurper.parse(FileUtils.fileTextContent(configFile))
    }

    private static CommandLine createCommandLine(String[] args, Options options) {
        DefaultParser parser = new DefaultParser()
        try {
            return parser.parse(options, args)
        } catch (ParseException e) {
            throw new RuntimeException(e)
        }
    }

    private def cliValue(String name, defaultValue) {
        return commandLine.hasOption(name) ? commandLine.getOptionValue(name) :
                defaultValue
    }

    private Options createOptions() {
        def options = new Options()
        options.addOption(null, "help", false, "print help")

        cfg.getCfgValuesStream().each {options.addOption(null, it.key, true, it.description)}
        return options
    }

    private Map commandLineArgsAsMap() {
        commandLine.options.collectEntries { [it.longOpt, it.value] }
    }
}