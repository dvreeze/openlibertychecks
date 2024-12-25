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

package eu.cdevreeze.openlibertychecks.xml.ibm.server;

import com.google.common.base.Preconditions;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

import javax.xml.namespace.QName;
import java.util.Optional;

/**
 * Element named "jmsQueue" in a server.xml file.
 *
 * @author Chris de Vreeze
 */
public final class JmsQueue implements ServerXmlJndiResource {

    private final AncestryAwareNodes.Element element;

    public JmsQueue(AncestryAwareNodes.Element element) {
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("jmsQueue"));
        this.element = element;
    }

    public AncestryAwareNodes.Element getElement() {
        return element;
    }

    public Optional<String> idOption() {
        return element.attributeOption(new QName("id"));
    }

    @Override
    public Optional<String> jndiNameOption() {
        return element.attributeOption(new QName("jndiName"));
    }
}
