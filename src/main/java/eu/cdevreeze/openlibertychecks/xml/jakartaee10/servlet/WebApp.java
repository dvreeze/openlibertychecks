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
import com.google.common.collect.ImmutableList;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.Listener;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.Names;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ResourceEnvRef;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.ResourceRef;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementTree;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementPredicates.hasName;

/**
 * Web app XML element wrapper. Corresponds to the contents of a web.xml file.
 *
 * @author Chris de Vreeze
 */
public final class WebApp implements WebXmlContent {

    private final ElementTree.Element element;

    public WebApp(ElementTree.Element element) {
        Preconditions.checkArgument(Names.JAKARTAEE_NS.equals(element.elementName().getNamespaceURI()));
        Preconditions.checkArgument(element.elementName().getLocalPart().equals("web-app"));

        this.element = element;
    }

    public ElementTree.Element getElement() {
        return element;
    }

    public ImmutableList<Servlet> servlets() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "servlet"))
                .map(Servlet::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<ServletMapping> servletMappings() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "servlet-mapping"))
                .map(ServletMapping::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<Filter> filters() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "filter"))
                .map(Filter::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<FilterMapping> filterMappings() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "filter-mapping"))
                .map(FilterMapping::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<Listener> listeners() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "listener"))
                .map(Listener::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<WelcomeFileList> welcomeFileLists() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "welcome-file-list"))
                .map(WelcomeFileList::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<ErrorPage> errorPages() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "error-page"))
                .map(ErrorPage::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<ResourceRef> resourceRefs() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "resource-ref"))
                .map(ResourceRef::new)
                .collect(ImmutableList.toImmutableList());
    }

    public ImmutableList<ResourceEnvRef> resourceEnvRefs() {
        String ns = element.elementName().getNamespaceURI();
        return element.childElementStream(hasName(ns, "resource-env-ref"))
                .map(ResourceEnvRef::new)
                .collect(ImmutableList.toImmutableList());
    }
}
