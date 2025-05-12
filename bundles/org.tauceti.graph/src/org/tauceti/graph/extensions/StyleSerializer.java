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

import org.tauceti.graph.Figure;
import org.tauceti.graph.util.SVGDOMWriter;
import org.w3c.dom.Element;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class StyleSerializer implements SVGExtensionAttributeSerializer {

	protected static StyleSerializer INSTANCE = new StyleSerializer();

	public static StyleSerializer getInstance() {
		return INSTANCE;
	}

	public StyleSerializer() {
	}

	@Override
	public void marshall(Figure shape, Element shapeElement, SVGExtensionAttribute extensionAttribute, SVGExtensionRegistry registry, SVGDOMWriter svgWriter) {
		if (extensionAttribute instanceof Style) {
			Style style = (Style) extensionAttribute;
			String name = style.getName();

			String styleValue = "";
			String keyValuesString = style.getPropertiesString();
			if (keyValuesString != null) {
				styleValue += keyValuesString;
			}
			String cssContent = style.getContent();
			if (cssContent != null) {
				styleValue += cssContent;
			}

			if (name != null && !styleValue.isEmpty()) {
				shapeElement.setAttribute(name, styleValue);
			}
		}
	}
}
