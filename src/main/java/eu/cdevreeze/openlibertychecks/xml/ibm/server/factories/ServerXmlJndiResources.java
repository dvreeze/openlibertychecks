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

package eu.cdevreeze.openlibertychecks.xml.ibm.server.factories;

import eu.cdevreeze.openlibertychecks.xml.ibm.server.*;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementTree;

import javax.xml.namespace.QName;
import java.util.Optional;

/**
 * ServerXmlJndiResources factory.
 *
 * @author Chris de Vreeze
 */
public class ServerXmlJndiResources {

    public static Optional<ServerXmlJndiResource> optionalInstance(ElementTree.Element element) {
        if (element.name().equals(new QName("dataSource"))) {
            return Optional.of(new DataSource(element));
        } else if (element.name().equals(new QName("jmsConnectionFactory"))) {
            return Optional.of(new JmsConnectionFactory(element));
        } else if (element.name().equals(new QName("jmsQueue"))) {
            return Optional.of(new JmsQueue(element));
        } else if (element.name().equals(new QName("jmsQueueConnectionFactory"))) {
            return Optional.of(new JmsQueueConnectionFactory(element));
        } else if (element.name().equals(new QName("jmsTopic"))) {
            return Optional.of(new JmsTopic(element));
        } else if (element.name().equals(new QName("jmsTopicConnectionFactory"))) {
            return Optional.of(new JmsTopicConnectionFactory(element));
        } else if (element.name().equals(new QName("jndiEntry"))) {
            return Optional.of(new JndiEntry(element));
        } else {
            return Optional.empty();
        }
    }

    public static ServerXmlJndiResource newInstance(ElementTree.Element element) {
        return optionalInstance(element).orElseThrow();
    }
}
