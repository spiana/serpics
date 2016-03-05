/**
 * Copyright 2015-2016 StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.serpics.admin.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.spring.server.SpringVaadinServlet;

/**
 * @author spiana
 *
 */
@WebServlet(value = {"/smc/*" , "/VAADIN/*"}, asyncSupported = true)
public class SmcVaadinStandAloneServlet extends SpringVaadinServlet {

}
