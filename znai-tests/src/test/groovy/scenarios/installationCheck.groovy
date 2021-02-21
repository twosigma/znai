/*
 * Copyright 2020 znai maintainers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package scenarios

import static clicommands.CliCommands.znai
import static org.testingisdocumenting.webtau.WebTauGroovyDsl.*

def scaffoldedDocRoot = cache.value("scaffoldedNewDocs")

scenario('shows help') {
    znai.run() {
        output.should contain('--new')
        output.should contain('create new documentation with minimal')

        exitCode.should == 1
    }
}

scenario('scaffolds new documentation') {
    def scaffoldDir = fs.tempDir('znai-scaffold')
    znai.run('--new', cli.workingDir(scaffoldDir))

    def docRoot = scaffoldDir.resolve('znai')
    fs.textContent(docRoot.resolve('toc')).should contain('chapter-one\n' +
            '    getting-started')

    scaffoldedDocRoot.set(docRoot.toString())
}

scenario('preview znai docs') {
    String docsRoot = scaffoldedDocRoot.get()

    def znaiPreview = znai.runInBackground("--preview", cli.workingDir(docsRoot))
    znaiPreview.output.waitTo contain(":3333")

    browser.open("http://localhost:3333/preview")
    $(".toc-panel-header").waitTo == 'Your Product Guide'

    znaiPreview.stop()
}