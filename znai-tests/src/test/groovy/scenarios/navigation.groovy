/*
 * Copyright 2021 znai maintainers
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

import static org.testingisdocumenting.webtau.WebTauGroovyDsl.*
import static pages.Pages.*

scenario('open docs') {
    browser.open("/")
}

scenario('navigating back and forth should preserve scroll position') {
    standardView.externalCodeSnippetsTocItem.click()
    standardView.externalCodeWideCodeSection.waitTo beVisible()

    standardView.externalCodeWideCodeSection.scrollIntoView()
    def scrollTopBeforeClick = standardView.mainPanelScrollTop.get()

    standardView.apiParametersTocItem.click()
    standardView.mainPanelScrollTop.waitTo == 0

    standardView.pageTitle.waitTo == "API Parameters"

    // TODO replace with webtau shorcut in 1.42
    browser.driver.navigate().back()

    standardView.mainPanelScrollTop.waitTo == scrollTopBeforeClick

    // TODO replace with webtau shorcut in 1.42
    browser.driver.navigate().forward()
    standardView.mainPanelScrollTop.waitTo == 0
}