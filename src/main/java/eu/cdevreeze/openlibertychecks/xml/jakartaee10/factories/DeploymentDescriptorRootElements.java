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

import eu.cdevreeze.openlibertychecks.xml.jakartaee10.DeploymentDescriptorRootElement;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.Names;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ejb.EjbJar;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet.WebApp;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementTree;

import java.util.Optional;

/**
 * DeploymentDescriptorRootElement factory.
 *
 * @author Chris de Vreeze
 */
public class DeploymentDescriptorRootElements {

    public static Optional<DeploymentDescriptorRootElement> optionalInstance(ElementTree.Element element) {
        if (element.name().equals(Names.JAKARTAEE_WEBAPP_NAME)) {
            return Optional.of(new WebApp(element));
        } else if (element.name().equals(Names.JAKARTAEE_EJBJAR_NAME)) {
            return Optional.of(new EjbJar(element));
        } else {
            return Optional.empty();
        }
    }

    public static DeploymentDescriptorRootElement newInstance(ElementTree.Element element) {
        return optionalInstance(element).orElseThrow();
    }
}
