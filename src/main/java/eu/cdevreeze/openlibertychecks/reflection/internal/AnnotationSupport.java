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

package eu.cdevreeze.openlibertychecks.reflection.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

/**
 * Annotation retrieval support (for annotations having retention "runtime").
 *
 * @author Chris de Vreeze
 */
public class AnnotationSupport {

    private AnnotationSupport() {
    }

    /**
     * Returns the result of method "getDeclaredAnnotation", wrapped in an "Optional".
     */
    public static <T extends Annotation> Optional<T> findDeclaredAnnotation(
            AnnotatedElement annotatedElement,
            Class<T> annotationClass
    ) {
        return Optional.ofNullable(annotatedElement.getDeclaredAnnotation(annotationClass));
    }

    /**
     * Returns the result of method "getAnnotation", wrapped in an "Optional".
     */
    public static <T extends Annotation> Optional<T> findAnnotation(
            AnnotatedElement annotatedElement,
            Class<T> annotationClass
    ) {
        return Optional.ofNullable(annotatedElement.getAnnotation(annotationClass));
    }
}
