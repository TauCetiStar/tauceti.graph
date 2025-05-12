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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.tauceti.graph.extensions.Attribute;
import org.tauceti.graph.extensions.Attributes;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AttributesImpl implements Attributes {

	protected LinkedHashMap<String, Attribute> attributesMap = new LinkedHashMap<String, Attribute>();

	@Override
	public void addAttribute(Attribute attribute) {
		this.attributesMap.put(attribute.getName(), attribute);
	}

	@Override
	public void setAttribute(String name, Object value) {
		Attribute attribute = new AttributeImpl();
		attribute.setName(name);
		attribute.setValue(value);
		this.attributesMap.put(attribute.getName(), attribute);
	}

	@Override
	public Attribute getAttribute(String name) {
		return this.attributesMap.get(name);
	}

	@Override
	public List<Attribute> getAttributes() {
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.addAll(this.attributesMap.values());
		return attributes;
	}
}
