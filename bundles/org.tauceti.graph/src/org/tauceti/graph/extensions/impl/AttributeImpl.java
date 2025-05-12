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

import javax.xml.namespace.QName;

import org.tauceti.graph.Figure;
import org.tauceti.graph.extensions.Attribute;
import org.tauceti.graph.extensions.SVGExtensionAttribute;
import org.tauceti.graph.util.SVGConstants;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AttributeImpl implements Attribute, SVGExtensionAttribute {

	protected Figure container;
	protected String name;
	protected Object value;
	protected boolean isPersistent = false;
	protected boolean isTransient = false;

	public AttributeImpl() {
	}

	@Override
	public QName getAttributeType() {
		return SVGConstants.ATTRIBUTE_QNAME;
	}

	@Override
	public void setContainer(Figure shape) {
		this.container = shape;
	}

	@Override
	public Figure getContainer() {
		return this.container;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return this.value;
	}

	public boolean isPersistent() {
		return this.isPersistent;
	}

	public void setPersistent(boolean isPersistent) {
		this.isPersistent = isPersistent;
	}

	public boolean isTransient() {
		return this.isTransient;
	}

	public void setTransient(boolean isTransient) {
		this.isTransient = isTransient;
	}
}
