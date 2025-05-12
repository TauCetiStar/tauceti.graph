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
package org.tauceti.graph.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class NSMap {

	protected Map<String, String> prefix2NamespaceMap = null;
	protected Map<String, List<String>> namespace2PrefixesMap = null;

	public NSMap() {
	}

	protected Map<String, String> getPrefix2NamespaceMap() {
		if (this.prefix2NamespaceMap == null) {
			this.prefix2NamespaceMap = new HashMap<String, String>(5);
		}
		return this.prefix2NamespaceMap;
	}

	protected List<String> getReverse4(String key) {
		if (this.namespace2PrefixesMap == null) {
			this.namespace2PrefixesMap = new HashMap<String, List<String>>(5);
		}
		List<String> prefixes = this.namespace2PrefixesMap.get(key);
		if (prefixes == null) {
			prefixes = new ArrayList<String>();
			this.namespace2PrefixesMap.put(key, prefixes);
		}
		return prefixes;
	}

	/**
	 * @param key the namespace to get the reverse mapping for
	 * @return The reverse mapping of the Namespace to namespace prefixes.
	 */
	public List<String> getReverse(String namespace) {
		return getReverse4(namespace);
	}

	public boolean containsKey(Object key) {
		return this.prefix2NamespaceMap != null ? this.prefix2NamespaceMap.containsKey(key) : false;
	}

	public boolean containsValue(Object value) {
		return this.prefix2NamespaceMap != null ? this.prefix2NamespaceMap.containsValue(value) : false;
	}

	public Set<java.util.Map.Entry<String, String>> entrySet() {
		if (this.prefix2NamespaceMap == null) {
			return Collections.emptySet();
		}
		return this.prefix2NamespaceMap.entrySet();
	}

	public String get(Object key) {
		return this.prefix2NamespaceMap != null ? this.prefix2NamespaceMap.get(key) : null;
	}

	public boolean isEmpty() {
		return this.prefix2NamespaceMap != null ? this.prefix2NamespaceMap.isEmpty() : true;
	}

	public Set<String> keySet() {
		if (this.prefix2NamespaceMap == null) {
			return Collections.emptySet();
		}
		return this.prefix2NamespaceMap.keySet();
	}

	public String put(String key, String value) {
		this.prefix2NamespaceMap = getPrefix2NamespaceMap();
		String oldValue = this.prefix2NamespaceMap.put(key, value);

		List<String> ns2pfx = getReverse4(value);
		if (ns2pfx.contains(key) == false) {
			ns2pfx.add(key);
		}
		return oldValue;
	}

	public void putAll(Map<? extends String, ? extends String> t) {
		this.prefix2NamespaceMap.putAll(t);
	}

	public String remove(Object key) {
		if (this.prefix2NamespaceMap == null) {
			return null;
		}

		String value = this.prefix2NamespaceMap.remove(key);
		if (value == null) {
			return value;
		}

		this.namespace2PrefixesMap.get(value).remove(key);
		return value;
	}

	public int size() {
		return this.prefix2NamespaceMap != null ? this.prefix2NamespaceMap.size() : 0;
	}

	public Collection<String> values() {
		if (this.prefix2NamespaceMap == null) {
			return Collections.emptyList();
		}
		return this.prefix2NamespaceMap.values();
	}

	public void clear() {
		if (this.namespace2PrefixesMap != null) {
			this.namespace2PrefixesMap.clear();
		}
		if (this.prefix2NamespaceMap != null) {
			this.prefix2NamespaceMap.clear();
		}
	}
}
