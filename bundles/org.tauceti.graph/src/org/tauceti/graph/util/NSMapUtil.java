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

import java.util.Map;

import org.tauceti.graph.Figure;
import org.tauceti.graph.adapter.Adaptable;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class NSMapUtil {

	/**
	 * 
	 * @param adaptable
	 * @return
	 */
	public static NSMap getNamespaceMap(Adaptable adaptable) {
		if (adaptable == null) {
			throw new IllegalArgumentException("Adaptable cannot be null");
		}

		NSMap nsMap = null;
		while (nsMap == null && adaptable != null) {
			nsMap = adaptable.getData(NSMap.class.getName(), NSMap.class);
			if (nsMap == null) {
				if (adaptable instanceof Figure) {
					Figure shape = (Figure) adaptable;
					adaptable = shape.getParent();
				} else {
					break;
				}
			}
		}
		if (nsMap == null) {
			throw new IllegalStateException("NSMap is not attached to an Adaptable");
		}
		return nsMap;
	}

	/**
	 * 
	 * @param adaptable
	 * @param namespace
	 * @param defaultPrefix
	 * @return
	 */
	public static String getPrefix(Adaptable adaptable, String namespace, String defaultPrefix) {
		String prefix = null;
		NSMap nsMap = getNamespaceMap(adaptable);
		if (nsMap != null && !nsMap.isEmpty()) {
			for (Map.Entry<String, String> entry : nsMap.entrySet()) {
				String currPrefix = entry.getKey();
				String currNamespace = entry.getValue();
				if (namespace.equals(currNamespace)) {
					prefix = currPrefix;
					break;
				}
			}
		}
		if (prefix == null) {
			prefix = defaultPrefix;
		}
		return prefix;
	}
}
