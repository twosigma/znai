/*
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

import {setupGlobalAssets} from "../global-assets/GlobalAssets"

export function stubGraphvizGlobalAssets() {
    setupGlobalAssets({
        "graphvizDiagram" : {
            "database" : "<g transform=\"translate(-205 -162)\">\n    <g transform=\"scale(0.15)\">\n        <path d=\"M1513.12,907.64L1513.12,907.691C1513.12,910.11 1512.9,912.503 1512.47,914.865L1513.12,914.865L1513.12,1140.31C1513.12,1182.12 1447.2,1216.06 1366,1216.06C1284.8,1216.06 1218.88,1182.12 1218.88,1140.31L1218.88,914.865L1219.53,914.865C1219.1,912.503 1218.88,910.11 1218.88,907.691L1218.88,907.64C1288.42,946.229 1317.35,962 1366,964.408C1429.66,967.559 1444.04,940.402 1513.12,907.64ZM1226.58,883.467C1246.22,853.522 1301.25,831.937 1366,831.937C1431.34,831.937 1486.78,853.916 1505.95,884.286C1444.84,921.241 1404.84,940.315 1366,939.949C1307.2,939.394 1271.99,905.341 1226.58,883.467Z\"/>\n    </g>\n</g>\n",
            "world" : "<g transform=\"scale(0.38)\">\n    <g transform=\"translate(-62 -62)\">\n        <path d=\"M62,0c-5,0-9.8,0.6-14.4,1.7C32.5,5.3,19.5,14.4,11,26.8C4.1,36.8,0,48.9,0,62c0,34.2,27.8,62,62,62s62-27.8,62-62   S96.2,0,62,0z M87.6,103.5c-0.5,4-6.199,6-7.399,1.7c-2.101-7.9-7.7-5.601-7.5-13c0.2-5.601-11.3-1.2-14.3-5.7   c-3-4.4,5.3-8.7,0.8-14.2S45.7,74.4,34.9,70.7c-4.8-1.7-9.4-5.601-12.6-9.5C18,56,16.5,49.8,14.7,43.4c-0.2-0.6-0.3-1.3-0.5-1.9   C19.7,28.8,30,18.7,42.9,13.6c2.4,3.3,10.1,1.7,15,2.2c2.5,0.2,4.3,1,4.4,3.2c0,0.4-0.1,1-0.3,1.6c-1.5,4.1-7.1,11.3-3.6,14   c2.6,2,4.4-1.8,5.9,3.9c0.7,2.6,1,8-1.899,9.9C59.6,50.2,52.6,43,44,47c-4.7,2.3-5.4,13.1,1.1,13.7c3.8,0.3,6.8-7.2,10.4-3.6   c2.9,2.9,5.4,10.8,12.2,11.1c6,0.3,17.5-3.4,25.1,7.1c4.5,6.4-3.8,11.5-5.3,17.601C86.2,97.9,88,100.5,87.6,103.5z\"/>\n    </g>\n</g>",
            "elasticSearch" : "<g transform=\"scale(0.25)\">\n    <g transform=\"translate(50, -525)\">\n        <path fill=\"#FED10A\" stroke=\"none\" d=\"M-62 484.8l51.8 23.6L42 462.6c0.8-3.8 1.1-7.5 1.1-11.5 0-32.2-26.2-58.4-58.4-58.4 -19.3 0-37.2 9.5-48.1 25.4l-8.7 45.1L-62 484.8z\"/>\n        <path fill=\"#24BBB1\" stroke=\"none\" d=\"M-115.6 539.6c-0.8 3.8-1.1 7.7-1.1 11.7 0 32.3 26.3 58.5 58.6 58.5 19.4 0 37.5-9.6 48.4-25.6l8.6-44.9 -11.5-22 -52-23.7L-115.6 539.6z\"/>\n        <path fill=\"#EF5098\" stroke=\"none\" d=\"M-115.9 450l35.5 8.4 7.8-40.3c-4.8-3.7-10.8-5.7-17-5.7 -15.4 0-28 12.5-28 28C-117.6 443.7-117 447-115.9 450\"/>\n        <path fill=\"#17A8E0\" stroke=\"none\" d=\"M-119 458.5c-15.8 5.2-26.9 20.4-26.9 37.1 0 16.3 10.1 30.8 25.2 36.6l49.8-45 -9.1-19.5L-119 458.5z\"/>\n        <path fill=\"#93C83E\" stroke=\"none\" d=\"M-0.7 584.2c4.9 3.7 10.8 5.8 16.9 5.8 15.4 0 28-12.5 28-28 0-3.4-0.6-6.7-1.7-9.7L7.1 544 -0.7 584.2z\"/>\n        <path fill=\"#0779A1\" stroke=\"none\" d=\"M6.5 534.7l39 9.1c15.9-5.2 26.9-20.4 26.9-37.2 0-16.2-10.1-30.8-25.2-36.5l-51 44.7L6.5 534.7z\"/>\n    </g>\n</g>",
            "document" : "<g transform=\"scale(0.1) translate(-170 -250)\"><g transform=\"matrix(1,0,0,1,-606,-787)\">\n" +
                "    <g transform=\"matrix(1,0,0,1,-12.9863,0)\">\n" +
                "        <path vector-effect=\"non-scaling-stroke\" stroke-width=\"1px\" d=\"M955.685,1260.78L619.949,1260.78L619.949,787.218L887.983,787.218L955.685,863.11L955.685,1260.78ZM949.337,873.749L878.884,873.749L878.884,794.772L949.337,873.749Z\"/>\n" +
                "    </g>\n" +
                "</g>></g>\n",
            "client" : "<g transform=\"scale(0.9)\">\n    <g transform=\"translate(-37, -30)\">\n        <path d=\"M58.944,49.454H11.053c-2.379,0-4.313,1.932-4.313,4.316v3.479c0,2.387,1.934,4.319,4.313,4.319h47.892\n\t\tc2.386,0,4.315-1.933,4.315-4.319v-3.479C63.26,51.386,61.33,49.454,58.944,49.454z M57.035,57.238H39.129\n\t\tc-0.953,0-1.728-0.772-1.728-1.729c0-0.952,0.774-1.726,1.728-1.726h17.906c0.954,0,1.728,0.773,1.728,1.726\n\t\tC58.763,56.466,57.989,57.238,57.035,57.238z\"/>\n        <path d=\"M13.313,45.937h43.317c3.603,0,6.535-2.933,6.535-6.537V14.974c0-3.608-2.933-6.542-6.535-6.542H13.313\n\t\tc-3.604,0-6.534,2.934-6.534,6.542v24.426C6.779,43.004,9.71,45.937,13.313,45.937z M11.35,14.974c0-1.087,0.881-1.971,1.964-1.971\n\t\th43.317c1.083,0,1.964,0.884,1.964,1.971v24.426c0,1.084-0.881,1.967-1.964,1.967H13.313c-1.083,0-1.964-0.883-1.964-1.967V14.974z\n\t\t\"/>\n    </g>\n</g>",
            "actor" : "<g transform=\"scale(0.1) translate(-170 -320)\"><g transform=\"matrix(1,0,0,1,-845,-557)\">\n" +
                "    <path vector-effect=\"non-scaling-stroke\" stroke-width=\"1px\"  d=\"M997.145,711.635C964.478,702.011 940.606,671.777 940.606,636.011C940.606,592.507 975.926,557.187 1019.43,557.187C1062.93,557.187 1098.25,592.507 1098.25,636.011C1098.25,671.777 1074.38,702.011 1041.71,711.635L1041.71,756.813L1193.47,756.813L1193.47,800.375L1041.71,800.375L1041.71,936.079L1193.47,1087.84L1162.67,1118.64L1019.43,975.401L876.19,1118.64L845.387,1087.84L997.145,936.079L997.145,800.375L845.387,800.375L845.387,756.813L997.145,756.813L997.145,711.635Z\"/>\n" +
                "</g></g>\n",
            "logStash" : "<g transform=\"scale(0.32)\">\n    <g transform=\"translate(-85, -100)\">\n        <g>\n            <path fill=\"#F2BD1A\" d=\"M86,99H0V0h26c33.1,0,60,26.9,60,60V99z\"/>\n            <path fill=\"#231F20\" d=\"M86,185L86,185c-47.5,0-86-38.5-86-86v0h86V185z\"/>\n            <rect x=\"86\" y=\"99\" fill=\"#40BEB0\" width=\"78.4\" height=\"86\"/>\n            <rect x=\"86\" y=\"99\" fill=\"#37A595\" width=\"18\" height=\"86\"/>\n        </g>\n    </g>\n</g>",
            "database-bottom-label" : "<g transform=\"translate(-205 -162)\">\n    <g transform=\"scale(0.15)\">\n        <path d=\"M1513.12,907.64L1513.12,907.691C1513.12,910.11 1512.9,912.503 1512.47,914.865L1513.12,914.865L1513.12,1140.31C1513.12,1182.12 1447.2,1216.06 1366,1216.06C1284.8,1216.06 1218.88,1182.12 1218.88,1140.31L1218.88,914.865L1219.53,914.865C1219.1,912.503 1218.88,910.11 1218.88,907.691L1218.88,907.64C1288.42,946.229 1317.35,962 1366,964.408C1429.66,967.559 1444.04,940.402 1513.12,907.64ZM1226.58,883.467C1246.22,853.522 1301.25,831.937 1366,831.937C1431.34,831.937 1486.78,853.916 1505.95,884.286C1444.84,921.241 1404.84,940.315 1366,939.949C1307.2,939.394 1271.99,905.341 1226.58,883.467Z\"/>\n    </g>\n</g>\n"
        }
    })
}