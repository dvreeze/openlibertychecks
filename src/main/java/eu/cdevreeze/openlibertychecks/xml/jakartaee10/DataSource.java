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
 * Data source XML element wrapper.
 *
 * @author Chris de Vreeze
 */
public final class DataSource implements JndiEnvironmentRefElement {

    private final AncestryAwareNodes.Element element;

    public DataSource(AncestryAwareNodes.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("data-source"));

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

    public Optional<String> classNameOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "class-name"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> serverNameOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "server-name"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public OptionalInt portNumberOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "port-number"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public Optional<String> databaseNameOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "database-name"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> urlOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "url"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> userOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "user"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> passwordOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "password"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public ImmutableList<Property> properties() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "property"))
                .map(Property::new)
                .collect(ImmutableList.toImmutableList());
    }

    public OptionalInt loginTimeoutOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "login-timeout"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public Optional<Boolean> transactionalOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "transactional"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text)
                .map(Boolean::valueOf);
    }

    public Optional<IsolationLevel> isolationLevelOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "isolation-level"))
                .map(AncestryAwareNodes.Element::text)
                .map(IsolationLevel::valueOf)
                .findFirst();
    }

    public OptionalInt initialPoolSizeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "initial-pool-size"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
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

    public OptionalInt maxIdleTimeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "max-idle-time"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }

    public OptionalInt maxStatementsOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "max-statements"))
                .map(AncestryAwareNodes.Element::text)
                .mapToInt(Integer::parseInt)
                .findFirst();
    }
}
