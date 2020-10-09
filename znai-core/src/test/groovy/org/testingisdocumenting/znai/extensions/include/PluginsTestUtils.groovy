/*
 * Copyright 2020 znai maintainers
 * Copyright 2019 TWO SIGMA OPEN SOURCE, LLC
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

package org.testingisdocumenting.znai.extensions.include

import org.testingisdocumenting.znai.core.AuxiliaryFile
import org.testingisdocumenting.znai.extensions.PluginParams
import org.testingisdocumenting.znai.extensions.Plugins
import org.testingisdocumenting.znai.extensions.fence.FencePlugin
import org.testingisdocumenting.znai.parser.docelement.DocElement
import org.testingisdocumenting.znai.parser.docelement.DocElementCreationParserHandler

import java.nio.file.Paths
import java.util.stream.Stream

import static org.testingisdocumenting.znai.parser.TestComponentsRegistry.TEST_COMPONENTS_REGISTRY

class PluginsTestUtils {
    static class IncludePluginAndParserHandler {
        IncludePlugin includePlugin
        DocElementCreationParserHandler parserHandler
    }

    static class FencePluginAndParserHandler {
        FencePlugin fencePlugin
        DocElementCreationParserHandler parserHandler
    }

    static Map<String, Object> processAndGetProps(String pluginDef) {
        def result = processInclude(pluginDef)
        return result[0].getProps()
    }

    static String processAndGetSimplifiedCodeBlock(String pluginDef) {
        return processAndGetProps(pluginDef).snippet
    }

    static List<DocElement> processInclude(String pluginDef) {
        def includePluginAndParserHandler = processAndGetIncludePluginAndParserHandler(pluginDef)
        return includePluginAndParserHandler.parserHandler.docElement.content
    }

    static Stream<AuxiliaryFile> processAndGetAuxiliaryFiles(String pluginDef) {
        def includePluginAndParserHandler = processAndGetIncludePluginAndParserHandler(pluginDef)
        return includePluginAndParserHandler.includePlugin.auxiliaryFiles(TEST_COMPONENTS_REGISTRY)
    }

    static IncludePlugin processAndGetIncludePlugin(String pluginDef) {
        def includePluginAndParserHandler = processAndGetIncludePluginAndParserHandler(pluginDef)
        return includePluginAndParserHandler.includePlugin
    }

    static IncludePluginAndParserHandler processAndGetIncludePluginAndParserHandler(String pluginDef) {
        DocElementCreationParserHandler parserHandler = new DocElementCreationParserHandler(
                TEST_COMPONENTS_REGISTRY,
                Paths.get(""))

        PluginParams includeParams = IncludePluginParser.parse(pluginDef)
        def includePlugin = Plugins.includePluginById(includeParams.pluginId)

        def pluginResult = includePlugin.process(TEST_COMPONENTS_REGISTRY, parserHandler, Paths.get(""), includeParams)

        parserHandler.onIncludePlugin(includePlugin, pluginResult)

        return new IncludePluginAndParserHandler(includePlugin: includePlugin, parserHandler: parserHandler)
    }

    static FencePluginAndParserHandler processAndGetFencePluginAndParserHandler(PluginParams pluginParams,
                                                                                String textContent) {
        DocElementCreationParserHandler parserHandler = new DocElementCreationParserHandler(
                TEST_COMPONENTS_REGISTRY,
                Paths.get(""))

        def fencePlugin = Plugins.fencePluginById(pluginParams.pluginId)

        def pluginResult = fencePlugin.process(TEST_COMPONENTS_REGISTRY, Paths.get(""),  pluginParams, textContent)
        parserHandler.onFencePlugin(fencePlugin, pluginResult)

        return new FencePluginAndParserHandler(fencePlugin: fencePlugin, parserHandler: parserHandler)
    }
}
