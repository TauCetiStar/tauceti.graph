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
public class AttributesSerializer implements SVGExtensionAttributeSerializer {

	protected static AttributesSerializer INSTANCE = new AttributesSerializer();

	public static AttributesSerializer getInstance() {
		return INSTANCE;
	}

	public AttributesSerializer() {
	}

	@Override
	public void marshall(Figure shape, Element shapeElement, SVGExtensionAttribute extensionAttribute, SVGExtensionRegistry registry, SVGDOMWriter svgWriter) {
		if (extensionAttribute instanceof Attributes) {
			Attributes attributesObj = (Attributes) extensionAttribute;
			for (Attribute attribute : attributesObj.getAttributes()) {
				String attrName = attribute.getName();
				Object attrValue = attribute.getValue();
				shapeElement.setAttribute(attrName, String.valueOf(attrValue));
			}
		}
	}
}
