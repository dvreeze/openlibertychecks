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

import eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet.Filter;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet.FilterMapping;
import eu.cdevreeze.openlibertychecks.xml.jakartaee10.servlet.WebApp;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareDocument;
import eu.cdevreeze.yaidom4j.dom.immutabledom.jaxpinterop.DocumentParsers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static eu.cdevreeze.yaidom4j.dom.ancestryaware.AncestryAwareElementPredicates.hasName;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Servlet dialect tests.
 * <p>
 * This is not a regular unit test.
 *
 * @author Chris de Vreeze
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServletDialectTests {

    private AncestryAwareDocument parseDocument() {
        InputStream inputStream = ServletDialectTests.class.getResourceAsStream("/dialects/sample-web.xml");
        return AncestryAwareDocument.from(
                DocumentParsers.builder().removingInterElementWhitespace().build()
                        .parse(new InputSource(inputStream))
        );
    }

    @Test
    public void testWebAppParsing() {
        AncestryAwareDocument doc = parseDocument();

        WebApp webApp = new WebApp(doc.documentElement());

        String ns = webApp.getElement().elementName().getNamespaceURI();
        assertEquals(Names.JAKARTAEE_NS, ns);

        assertEquals(
                "Servlet 3.0 application",
                webApp.getElement().childElementStream(hasName(ns, "display-name")).findFirst().orElseThrow().text()
        );

        assertEquals(1, webApp.filters().size());

        Filter filter = webApp.filters().get(0);

        assertEquals("ServletMappedDoFilter_Filter", filter.filterName());
        assertEquals("tests.Filter.DoFilter_Filter", filter.filterClassOption().orElse(""));
        assertEquals(
                List.of(
                        List.of("attribute", "tests.Filter.DoFilter_Filter.SERVLET_MAPPED")
                ),
                filter.initParams().stream().map(p -> List.of(p.paramName(), p.paramValue())).toList()
        );

        assertEquals(
                List.of(
                        List.of("ServletMappedDoFilter_Filter", List.of("/DoFilterTest"), List.of(FilterMapping.Dispatcher.REQUEST)),
                        List.of("ServletMappedDoFilter_Filter", List.of("/IncludedServlet"), List.of(FilterMapping.Dispatcher.INCLUDE)),
                        List.of("ServletMappedDoFilter_Filter", List.of("ForwardedServlet"), List.of(FilterMapping.Dispatcher.FORWARD))
                ),
                webApp.filterMappings().stream()
                        .map(mapping -> List.of(mapping.filterName(), mapping.urlPatterns(), mapping.dispatchers()))
                        .toList()
        );

        assertEquals(
                List.of("tests.ContextListener", "tests.ServletRequestListener.RequestListener"),
                webApp.listeners().stream().map(Listener::listenerClass).toList()
        );

        assertEquals(
                List.of(
                        List.of("welcome", "WelcomeServlet"),
                        List.of("ServletErrorPage", "tests.Error.ServletErrorPage"),
                        List.of("IncludedServlet", "tests.Filter.IncludedServlet"),
                        List.of("ForwardedServlet", "tests.Filter.ForwardedServlet")
                ),
                webApp.servlets()
                        .stream()
                        .map(servlet -> List.of(servlet.servletName(), servlet.servletClassOption().orElse("")))
                        .toList()
        );

        assertEquals(
                List.of(
                        List.of("welcome", List.of("/hello.welcome")),
                        List.of("ServletErrorPage", List.of("/ServletErrorPage")),
                        List.of("IncludedServlet", List.of("/IncludedServlet")),
                        List.of("ForwardedServlet", List.of("/ForwardedServlet"))
                ),
                webApp.servletMappings()
                        .stream()
                        .map(servletMapping -> List.of(servletMapping.servletName(), servletMapping.urlPatterns()))
                        .toList()
        );

        assertEquals(
                List.of("hello.welcome"),
                webApp.welcomeFileLists().stream().flatMap(v -> v.welcomeFiles().stream()).toList()
        );

        assertEquals(
                List.of(
                        List.of("/ServletErrorPage", Optional.empty(), Optional.of("java.lang.ArrayIndexOutOfBoundsException")),
                        List.of("/error404.html", Optional.of("404"), Optional.empty())
                ),
                webApp.errorPages().stream().map(e -> List.of(e.location(), e.errorCodeOption(), e.exceptionTypeOption())).toList()
        );
    }
}
