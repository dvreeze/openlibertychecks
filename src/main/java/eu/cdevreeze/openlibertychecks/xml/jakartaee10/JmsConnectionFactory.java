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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementTree;

import javax.xml.namespace.QName;
import java.util.Optional;
import java.util.OptionalInt;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementPredicates.hasName;

/**
 * JMS Connection factory XML element wrapper.
 *
 * @author Chris de Vreeze
 */
public final class JmsConnectionFactory implements JakartaEEXmlContent {

    private final ElementTree.Element element;

    public JmsConnectionFactory(ElementTree.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("jms-connection-factory"));

        this.element = element;
    }

    public ElementTree.Element getElement() {
        return element;
    }

    public Optional<String> idOption() {
        return element.attributeOption(new QName("id"));
    }

    /**
     * Returns the JNDI name
     */
    public String name() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "name"))
                .findFirst()
                .orElseThrow()
                .text();
    }

    public Optional<String> interfaceNameOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "interface-name"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public Optional<String> classNameOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "class-name"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public Optional<String> resourceAdapterOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "resource-adapter"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public Optional<String> userOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "user"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public Optional<String> passwordOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "password"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public Optional<String> clientIdOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "client-id"))
                .findFirst()
                .map(ElementTree.Element::text);
    }

    public ImmutableList<Property> properties() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "property"))
                .map(Property::new)
                .collect(ImmutableList.toImmutableList());
    }

    public Optional<Boolean> transactionalOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "transactional"))
                .findFirst()
                .map(ElementTree.Element::text)
                .map(Boolean::valueOf);
    }

    public OptionalInt maxPoolSizeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "max-pool-size"))
                .map(ElementTree.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public OptionalInt minPoolSizeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "min-pool-size"))
                .map(ElementTree.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }
}
