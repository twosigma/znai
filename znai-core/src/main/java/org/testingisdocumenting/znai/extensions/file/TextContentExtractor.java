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

package org.testingisdocumenting.znai.extensions.file;

import org.testingisdocumenting.znai.extensions.PluginParamsOpts;
import org.testingisdocumenting.znai.utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class TextContentExtractor {
    private TextContentExtractor() {
    }

    public static String extractText(String contentId, String content, PluginParamsOpts opts) {
        if (opts.isEmpty()) {
            return content;
        }

        Text text = new Text(contentId, content);
        Text surroundedBy = cropSurroundedBy(text, opts);
        Text croppedAtStart = cropStart(surroundedBy, opts);
        Text croppedAtEnd = cropEnd(croppedAtStart, opts);

        Text withExcludedStartEnd = excludeStartEnd(croppedAtEnd, opts);
        Text withIncludeRegexp = includeRegexp(withExcludedStartEnd, opts);
        Text withExcludedRegexp = excludeRegexp(withIncludeRegexp, opts);

        return StringUtils.stripIndentation(withExcludedRegexp.toString());
    }

    private static Text cropSurroundedBy(Text text, PluginParamsOpts opts) {
        String surroundedBy = opts.get("surroundedBy");
        if (surroundedBy == null) {
            return text;
        }

        Text start = text.startingWithLineContaining(surroundedBy);
        return start.limitToLineContaining(surroundedBy)
                .cropOneLineFromStartAndEnd();
    }

    private static Text cropStart(Text text, PluginParamsOpts opts) {
        String startLine = opts.get("startLine");
        if (startLine == null) {
            return text;
        }

        return text.startingWithLineContaining(startLine);
    }

    private static Text cropEnd(Text text, PluginParamsOpts opts) {
        Number numberOfLines = opts.get("numberOfLines");
        if (numberOfLines != null) {
            return text.limitTo(numberOfLines);
        }

        String endLine = opts.get("endLine");
        if (endLine != null) {
            return text.limitToLineContaining(endLine);
        }

        return text;
    }

    private static Text excludeStartEnd(Text text, PluginParamsOpts opts) {
        Boolean exclude = opts.get("excludeStartEnd", false);
        if (!exclude) {
            return text;
        }

        boolean hasStartLine = opts.has("startLine");
        boolean hasEndLine = opts.has("endLine");
        if ((hasStartLine && hasEndLine) || (!hasStartLine && !hasEndLine)) {
            return text.cropOneLineFromStartAndEnd();
        }

        if (hasStartLine) {
            return text.cropOneLineFromStart();
        }

        return text.cropOneLineFromEnd();
    }

    private static Text includeRegexp(Text text, PluginParamsOpts opts) {
        List<String> includeRegexps = opts.getList("includeRegexp");
        if (includeRegexps.isEmpty()) {
            return text;
        }

        return text.includeRegexp(createListOfPatterns(includeRegexps));
    }

    private static Text excludeRegexp(Text text, PluginParamsOpts opts) {
        List<String> excludeRegexps = opts.getList("excludeRegexp");
        if (excludeRegexps.isEmpty()) {
            return text;
        }

        return text.excludeRegexp(createListOfPatterns(excludeRegexps));
    }

    private static List<Pattern> createListOfPatterns(List<String> regexps) {
        return regexps.stream().map(Pattern::compile).collect(toList());
    }

    private static class Text {
        private final String contentId;
        private final List<String> lines;
        private final boolean hasCroppedStart;

        public Text(String contentId, String text) {
            this(contentId, Arrays.asList(text.split("\n")), false);
        }

        public Text(String contentId, List<String> lines, boolean hasCroppedStart) {
            this.contentId = contentId;
            this.lines = lines;
            this.hasCroppedStart = hasCroppedStart;
        }

        Text startingWithLineContaining(String subLine) {
            int lineIdx = findLineIdxContaining(subLine);
            return newText(lines.subList(lineIdx, lines.size()), true);
        }

        Text limitToLineContaining(String subLine) {
            int lineIdx = findLineIdxContaining(subLine);
            return newText(lines.subList(0, lineIdx + 1));
        }

        Text limitTo(Number numberOfLines) {
            return newText(lines.subList(0, numberOfLines.intValue()));
        }

        Text cropOneLineFromStartAndEnd() {
            return newText(lines.subList(1, lines.size() - 1));
        }

        Text cropOneLineFromStart() {
            return newText(lines.subList(1, lines.size()));
        }

        Text cropOneLineFromEnd() {
            return newText(lines.subList(0, lines.size() - 1));
        }

        Text includeRegexp(List<Pattern> regexps) {
            List<String> newLines = lines.stream()
                    .filter(line -> regexps.stream().anyMatch(r -> r.matcher(line).find()))
                    .collect(toList());

            if (newLines.isEmpty()) {
                throw new IllegalArgumentException("there are no lines matching includeRegexp " +
                        renderListOfRegexp(regexps) + renderInContent());
            }
            return newText(newLines);
        }

        Text excludeRegexp(List<Pattern> regexps) {
            List<String> newLines = lines.stream()
                    .filter(line -> regexps.stream().noneMatch(r -> r.matcher(line).find()))
                    .collect(toList());

            if (newLines.size() == lines.size()) {
                throw new IllegalArgumentException("there are no lines matching excludeRegexp " +
                        renderListOfRegexp(regexps) + renderInContent());
            }

            return newText(newLines);
        }

        private Text newText(List<String> lines) {
            return new Text(contentId, lines, false);
        }

        private Text newText(List<String> lines, boolean hasCroppedStart) {
            return new Text(contentId, lines, hasCroppedStart);
        }

        private int findLineIdxContaining(String subLine) {
            for (int i = hasCroppedStart ? 1 : 0; i < lines.size(); i++) {
                if (lines.get(i).contains(subLine)) {
                    return i;
                }
            }

            throw new IllegalArgumentException("there is no line containing \"" + subLine + "\"" + renderInContent());
        }

        @Override
        public String toString() {
            return String.join("\n", lines);
        }

        private String renderInContent() {
            return " in <" + contentId + ">:\n" + this;
        }

        private String renderListOfRegexp(List<Pattern> regexps) {
            return regexps.stream().map(p -> "<" + p.toString() + ">")
                    .collect(Collectors.joining(", "));
        }
    }
}
