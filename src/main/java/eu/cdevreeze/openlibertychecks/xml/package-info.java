/*
 * Copyright 2024-2024 Chris de Vreeze
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

/**
 * Very lightweight querying support for several XML dialects of Jakarta EE and OpenLiberty configuration
 * files. These support class wrap underlying XML "DOM" elements, for some very common parts of the
 * dialect. This support makes querying for a dialect a bit friendlier than plain XML querying (using only
 * the yaidom4j element query API).
 *
 * @author Chris de Vreeze
 */
package eu.cdevreeze.openlibertychecks.xml;
