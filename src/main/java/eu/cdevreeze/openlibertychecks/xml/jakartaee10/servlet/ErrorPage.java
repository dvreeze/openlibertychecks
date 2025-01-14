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

package eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet;

import com.google.common.base.Preconditions;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.Names;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

import javax.xml.namespace.QName;
import java.util.Optional;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareElementPredicates.hasName;

/**
 * Error page XML element wrapper.
 *
 * @author Chris de Vreeze
 */
public final class ErrorPage implements WebXmlContent {

    private final AncestryAwareNodes.Element element;

    public ErrorPage(AncestryAwareNodes.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("error-page"));

        this.element = element;
    }

    public AncestryAwareNodes.Element getElement() {
        return element;
    }

    public Optional<String> idOption() {
        return element.attributeOption(new QName("id"));
    }

    public Optional<String> errorCodeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "error-code"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> exceptionTypeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "exception-type"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public String location() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "location"))
                .findFirst()
                .orElseThrow()
                .text();
    }
}
