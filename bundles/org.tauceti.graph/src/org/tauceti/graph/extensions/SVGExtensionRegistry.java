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

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SVGExtensionRegistry {

	protected static SVGExtensionRegistry INSTANCE;

	public synchronized static SVGExtensionRegistry getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SVGExtensionRegistry();
		}
		return INSTANCE;
	}

	protected Map<QName, SVGExtensionElementSerializer> extensionElementSerializers;
	protected Map<QName, SVGExtensionElementDeserializer> extensionElementDeserializers;
	protected Map<QName, SVGExtensionAttributeSerializer> extensionAttributeSerializers;
	protected Map<QName, SVGExtensionAttributeDeserializer> extensionAttributeDeserializers;

	public SVGExtensionRegistry() {
		this.extensionElementSerializers = new HashMap<QName, SVGExtensionElementSerializer>();
		this.extensionElementDeserializers = new HashMap<QName, SVGExtensionElementDeserializer>();
		this.extensionAttributeSerializers = new HashMap<QName, SVGExtensionAttributeSerializer>();
		this.extensionAttributeDeserializers = new HashMap<QName, SVGExtensionAttributeDeserializer>();
	}

	/**
	 * Register extension element serializer.
	 * 
	 * @param elementType
	 * @param serializer
	 */
	public void registerExtensionElementSerializer(QName elementType, SVGExtensionElementSerializer serializer) {
		this.extensionElementSerializers.put(elementType, serializer);
	}

	/**
	 * Register extension element deserializer.
	 * 
	 * @param elementType
	 * @param deserializer
	 */
	public void registerExtensionElementDeserializer(QName elementType, SVGExtensionElementDeserializer deserializer) {
		this.extensionElementDeserializers.put(elementType, deserializer);
	}

	/**
	 * Register extension attribute serializer.
	 * 
	 * @param attributeType
	 * @param serializer
	 */
	public void registerExtensionAttributeSerializer(QName attributeType, SVGExtensionAttributeSerializer serializer) {
		this.extensionAttributeSerializers.put(attributeType, serializer);
	}

	/**
	 * Register extension attribute deserializer.
	 * 
	 * @param attributeType
	 * @param deserializer
	 */
	public void registerExtensionAttributeDeserializer(QName attributeType, SVGExtensionAttributeDeserializer deserializer) {
		this.extensionAttributeDeserializers.put(attributeType, deserializer);
	}

	/**
	 * @param elementType
	 * @return return the extension serializer.
	 */
	public SVGExtensionElementSerializer getExtensionElementSerializer(QName elementType) {
		if (elementType == null) {
			return null;
		}
		return this.extensionElementSerializers.get(elementType);
	}

	/**
	 * @param elementType
	 * @return return the extension deserializer.
	 */
	public SVGExtensionElementDeserializer getExtensionElementDeserializer(QName elementType) {
		if (elementType == null) {
			return null;
		}
		return this.extensionElementDeserializers.get(elementType);
	}

	/**
	 * @param attributeType
	 * @return return the attribute serializer.
	 */
	public SVGExtensionAttributeSerializer getExtensionAttributeSerializer(QName attributeType) {
		if (attributeType == null) {
			return null;
		}
		return this.extensionAttributeSerializers.get(attributeType);
	}

	/**
	 * @param attributeType
	 * @return return the attribute deserializer.
	 */
	public SVGExtensionAttributeDeserializer getExtensionAttributeDeserializer(QName attributeType) {
		if (attributeType == null) {
			return null;
		}
		return this.extensionAttributeDeserializers.get(attributeType);
	}
}
