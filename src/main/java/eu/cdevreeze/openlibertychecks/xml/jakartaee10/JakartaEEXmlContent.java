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

package eu.cdevreeze.openlibertychecks.xml.jakartaee10;

import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

/**
 * Any XML element in a standard Jakarta EE XML configuration file that gets its own class.
 * <p>
 * For the related XML schemas, see <a href="https://jakarta.ee/xml/ns/jakartaee/">Jakarta EE XML Schemas</a>.
 *
 * @author Chris de Vreeze
 */
public interface JakartaEEXmlContent {

    AncestryAwareNodes.Element getElement();
}
