/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.graph.extensions;

import javax.xml.namespace.QName;

import org.tauceti.graph.Figure;
import org.tauceti.graph.util.SVGConstants;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ScriptDefinition implements SVGExtensionElement {

	protected Figure container;
	protected String href;

	public ScriptDefinition() {
	}

	@Override
	public QName getElementType() {
		return SVGConstants.SCRIPT_DEFINITION_QNAME;
	}

	@Override
	public void setContainer(Figure shape) {
		this.container = shape;
	}

	@Override
	public Figure getContainer() {
		return this.container;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}
}
