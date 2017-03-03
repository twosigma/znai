package com.twosigma.documentation.extensions.include

import com.twosigma.documentation.parser.TestComponentsRegistry
import org.junit.Assert
import org.junit.Test

import java.nio.file.Paths

/**
 * @author mykola
 */
class TextFileIncludePluginTest {
    @Test
    void "should extract file snippet based on start line and number of lines"() {
        def text = process("{startLine: 'multiple lines', numberOfLines: 2}")

        Assert.assertEquals("a multiple lines\n" +
                "line number", text)
    }

    @Test
    void "should extract file snippet based on start and stop end lines"() {
        def text = process("{startLine: 'multiple lines', endLine: 'stop'}")

        Assert.assertEquals("a multiple lines\n" +
                "line number\n" +
                "--- stop", text)
    }

    @Test
    void "should extract file snippet based on start and stop end lines excluding them"() {
        def text = process("{startLine: 'number', endLine: 'stop', exclude: true}")

        Assert.assertEquals("", text)
    }

    @Test
    void "should extract file snippet based on start line only"() {
        def text = process("{startLine: 'multiple lines'}")

        Assert.assertEquals("a multiple lines\n" +
                "line number\n" +
                "--- stop\n" +
                "and five\n" +
                "and then six", text)
    }

    @Test
    void "should extract file snippet based on end line only"() {
        def text = process("{endLine: 'stop'}")

        Assert.assertEquals("this is a\n" +
                "test file in\n" +
                "a multiple lines\n" +
                "line number\n" +
                "--- stop", text)
    }

    private static String process(String value) {
        def plugin = new TextFileIncludePlugin()
        def result = plugin.process(new TestComponentsRegistry(), Paths.get(""), new IncludeParams("test-file.txt $value"))

        return result.docElements.get(0).getProps().componentProps.tokens[0].data
    }
}
