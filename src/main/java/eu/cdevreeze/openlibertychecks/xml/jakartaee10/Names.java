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

import javax.xml.namespace.QName;

/**
 * Constants for names and namespaces.
 *
 * @author Chris de Vreeze
 */
public class Names {

    private Names() {
    }

    public static final String JAKARTAEE_NS = "https://jakarta.ee/xml/ns/jakartaee";

    public static final QName JAKARTAEE_WEBAPP_NAME = new QName(JAKARTAEE_NS, "web-app");
    public static final QName JAKARTAEE_EJBJAR_NAME = new QName(JAKARTAEE_NS, "ejb-jar");

    public static final QName JAKARTAEE_ENTITY_NAME = new QName(JAKARTAEE_NS, "entity");
    public static final QName JAKARTAEE_SESSION_NAME = new QName(JAKARTAEE_NS, "session");
    public static final QName JAKARTAEE_MESSAGE_DRIVEN_NAME = new QName(JAKARTAEE_NS, "message-driven");
    public static final QName JAKARTAEE_INTERCEPTOR_NAME = new QName(JAKARTAEE_NS, "interceptor");
}
