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

package eu.cdevreeze.openlibertychecks.console.internal;

import com.google.common.collect.ImmutableList;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.Document;
import eu.cdevreeze.yaidom4j.dom.ancestryaware.ElementTree;
import eu.cdevreeze.yaidom4j.dom.immutabledom.jaxpinterop.DocumentParser;
import eu.cdevreeze.yaidom4j.dom.immutabledom.jaxpinterop.DocumentParsers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Utility to find certain XML file root elements.
 *
 * @author Chris de Vreeze
 */
public class XmlRootElementFinder {

    private XmlRootElementFinder() {
    }

    /**
     * Finds all XML file root elements of XML files under the given search root directory,
     * matching the given XML file predicate, and matching the given root element predicate.
     */
    public static ImmutableList<ElementTree.Element> findXmlRootElements(
            Path dir,
            Predicate<Path> xmlFilePredicate,
            Predicate<ElementTree.Element> rootElementPredicate
    ) {
        DocumentParser docParser = DocumentParsers.builder().removingInterElementWhitespace().build();

        try (Stream<Path> fileStream = Files.walk(dir)) {
            return fileStream
                    .filter(Files::isRegularFile)
                    .filter(xmlFilePredicate)
                    .map(p ->
                            Document.from(docParser.parse(p.toUri()))
                                    .withUri(dir.toUri())
                                    .documentElement()
                    )
                    .filter(rootElementPredicate)
                    .collect(ImmutableList.toImmutableList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
