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
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

import javax.xml.namespace.QName;
import java.util.Optional;
import java.util.OptionalInt;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareElementPredicates.hasName;

/**
 * Connector Connection factory resource XML element wrapper.
 *
 * @author Chris de Vreeze
 */
public final class ConnectionFactory implements JndiEnvironmentRefElement {

    private final AncestryAwareNodes.Element element;

    public ConnectionFactory(AncestryAwareNodes.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("connection-factory"));

        this.element = element;
    }

    public AncestryAwareNodes.Element getElement() {
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

    public String interfaceName() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "interface-name"))
                .findFirst()
                .orElseThrow()
                .text();
    }

    public String resourceAdapter() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "resource-adapter"))
                .findFirst()
                .orElseThrow()
                .text();
    }

    public OptionalInt maxPoolSizeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "max-pool-size"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public OptionalInt minPoolSizeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "min-pool-size"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public Optional<Boolean> transactionSupportOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "transaction-support"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text)
                .map(Boolean::valueOf);
    }

    public ImmutableList<Property> properties() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "property"))
                .map(Property::new)
                .collect(ImmutableList.toImmutableList());
    }
}
