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

package eu.cdevreeze.openlibertychecks.xml.jakartaee10.factories;

import eu.cdevreeze.openlibertychecks.xml.jakartaee10.JndiResourceContainerElement;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.Names;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ejb.EntityBean;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ejb.Interceptor;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ejb.MessageDrivenBean;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ejb.SessionBean;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet.WebApp;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareNodes;

import java.util.Optional;

/**
 * JndiResourceContainerElement factory.
 *
 * @author Chris de Vreeze
 */
public class JndiResourceContainerElements {

    public static Optional<JndiResourceContainerElement> optionalInstance(AncestryAwareNodes.Element element) {
        if (element.name().equals(Names.JAKARTAEE_WEBAPP_NAME)) {
            return Optional.of(new WebApp(element));
        } else if (element.name().equals(Names.JAKARTAEE_ENTITY_NAME)) {
            return Optional.of(new EntityBean(element));
        } else if (element.name().equals(Names.JAKARTAEE_SESSION_NAME)) {
            return Optional.of(new SessionBean(element));
        } else if (element.name().equals(Names.JAKARTAEE_MESSAGE_DRIVEN_NAME)) {
            return Optional.of(new MessageDrivenBean(element));
        } else if (element.name().equals(Names.JAKARTAEE_INTERCEPTOR_NAME)) {
            return Optional.of(new Interceptor(element));
        } else {
            return Optional.empty();
        }
    }

    public static JndiResourceContainerElement newInstance(AncestryAwareNodes.Element element) {
        return optionalInstance(element).orElseThrow();
    }
}
