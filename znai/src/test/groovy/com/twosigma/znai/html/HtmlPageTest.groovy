package com.twosigma.znai.html

import org.junit.Test

import static com.twosigma.webtau.Ddjt.contain

class HtmlPageTest {
    @Test
    void "should add custom render to body"() {
        def page = new HtmlPage()
        page.addToBody { 'custom body block' }

        def rendered = page.render('my-doc')
        rendered.should contain('<body>\ncustom body block')
    }

    @Test
    void "should add custom render to head"() {
        def page = new HtmlPage()
        page.addToHead { 'custom head block' }

        def rendered = page.render('my-doc')
        rendered.should contain('custom head block\n</head>')
    }
}
