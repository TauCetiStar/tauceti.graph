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
package org.tauceti.graph.extensions.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;

import org.tauceti.graph.Figure;
import org.tauceti.graph.extensions.SVGExtensionAttribute;
import org.tauceti.graph.extensions.Style;
import org.tauceti.graph.util.SVGConstants;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class StyleImpl implements Style, SVGExtensionAttribute {

	protected Figure container;
	protected String name;
	protected Map<String, String> propertiesMap = new HashMap<String, String>();
	protected String content;

	public StyleImpl() {
		this("style");
	}

	@Override
	public QName getAttributeType() {
		return SVGConstants.STYLE_DEFINITION_QNAME;
	}

	@Override
	public void setContainer(Figure shape) {
		this.container = shape;
	}

	@Override
	public Figure getContainer() {
		return this.container;
	}

	public StyleImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Map<String, String> getPropertiesMap() {
		return this.propertiesMap;
	}

	@Override
	public void setProperty(String key, String value) {
		if (key == null || key.isEmpty()) {
			return;
		}
		if (value != null) {
			this.propertiesMap.put(key, value);
		} else {
			this.propertiesMap.remove(key);
		}
	}

	@Override
	public void removeProperty(String key) {
		this.propertiesMap.remove(key);
	}

	@Override
	public String getPropertiesString() {
		StringBuilder content = new StringBuilder();
		for (Iterator<Entry<String, String>> entryItor = this.propertiesMap.entrySet().iterator(); entryItor.hasNext();) {
			Entry<String, String> entry = entryItor.next();
			String propName = entry.getKey();
			String propValue = entry.getValue();
			content.append(propName + ":" + propValue + ";");
		}
		return content.toString();
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}
}
