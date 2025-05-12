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
package org.tauceti.graph.proxy;

import org.tauceti.graph.ClipPath;
import org.tauceti.graph.Filter;
import org.tauceti.graph.resolver.ResolverRegistry;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ProxyHelper {

	/**
	 * 
	 * @param source
	 * @param filterId
	 * @return
	 */
	public static Filter resolveFilter(Object source, String filterId) {
		return ResolverRegistry.getInstance().getFilterResolver().resolve(source, filterId);
	}

	/**
	 * 
	 * @param source
	 * @param clipPathId
	 * @return
	 */
	public static ClipPath resolveClipPath(Object source, String clipPathId) {
		return ResolverRegistry.getInstance().getClipPathResolver().resolve(source, clipPathId);
	}
}
