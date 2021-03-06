/*
 * Copyright 2021 znai maintainers
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

export interface AnnotationStyle {
  line: string;
  fill: string;
  text: string;
  fontSize: number | string;
  lineWidth: number | string;
}

export function styleByName(name = "a", inverted = false): AnnotationStyle {
  return {
    line: `var(--znai-diagram-${name}-line)`,
    fill: `var(--znai-diagram-${name}-fill)`,
    lineWidth: 4,
    fontSize: "",
    text: inverted
      ? `var(--znai-diagram-${name}-text-inverse)`
      : `var(--znai-diagram-${name}-text)`,
  };
}
