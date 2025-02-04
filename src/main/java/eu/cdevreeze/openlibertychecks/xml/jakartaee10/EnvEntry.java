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
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

import javax.xml.namespace.QName;
import java.util.Optional;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareElementPredicates.hasName;

/**
 * Env entry XML element wrapper.
 *
 * @author Chris de Vreeze
 */
public final class EnvEntry implements JndiEnvironmentRefElement {

    private final AncestryAwareNodes.Element element;

    public EnvEntry(AncestryAwareNodes.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("env-entry"));

        this.element = element;
    }

    public AncestryAwareNodes.Element getElement() {
        return element;
    }

    public Optional<String> idOption() {
        return element.attributeOption(new QName("id"));
    }

    @Override
    public String name() {
        return envEntryName();
    }

    /**
     * Returns the JNDI name relative to the java:comp/env context.
     */
    public String envEntryName() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "env-entry-name"))
                .findFirst()
                .orElseThrow()
                .text();
    }

    public Optional<String> envEntryTypeOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "env-entry-type"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }

    public Optional<String> envEntryValueOption() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "env-entry-value"))
                .findFirst()
                .map(AncestryAwareNodes.Element::text);
    }
}
