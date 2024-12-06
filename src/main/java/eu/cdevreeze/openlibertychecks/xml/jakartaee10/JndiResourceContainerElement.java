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

import com.google.common.collect.ImmutableList;

import java.util.function.Function;
import java.util.stream.Stream;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementPredicates.hasName;

/**
 * Any container of JNDI environment references, such as EJBs, the web-app root element etc.
 *
 * @author Chris de Vreeze
 */
public interface JndiResourceContainerElement extends JakartaEEXmlContent {

    default ImmutableList<EnvEntry> envEntries() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "env-entry"))
                .map(EnvEntry::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<ResourceRef> resourceRefs() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "resource-ref"))
                .map(ResourceRef::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<ResourceEnvRef> resourceEnvRefs() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "resource-env-ref"))
                .map(ResourceEnvRef::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<DataSource> dataSources() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "data-source"))
                .map(DataSource::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<JmsConnectionFactory> jmsConnectionFactories() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "jms-connection-factory"))
                .map(JmsConnectionFactory::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<JmsDestination> jmsDestinations() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "jms-destination"))
                .map(JmsDestination::new)
                .collect(ImmutableList.toImmutableList());
    }

    default ImmutableList<ConnectionFactory> connectionFactories() {
        String ns = getElement().elementName().getNamespaceURI();
        return getElement().childElementStream(hasName(ns, "connection-factory"))
                .map(ConnectionFactory::new)
                .collect(ImmutableList.toImmutableList());
    }

    /**
     * Returns JndiEnvironmentRefElement instances of known JndiEnvironmentRefElement subtypes
     */
    static ImmutableList<JndiEnvironmentRefElement> findJndiEnvironmentRefElements(JndiResourceContainerElement element) {
        return Stream.of(
                element.envEntries().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.resourceRefs().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.resourceEnvRefs().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.dataSources().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.jmsConnectionFactories().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.jmsDestinations().stream().map(e -> (JndiEnvironmentRefElement) e),
                element.connectionFactories().stream().map(e -> (JndiEnvironmentRefElement) e)
        ).flatMap(Function.identity()).collect(ImmutableList.toImmutableList());
    }
}
