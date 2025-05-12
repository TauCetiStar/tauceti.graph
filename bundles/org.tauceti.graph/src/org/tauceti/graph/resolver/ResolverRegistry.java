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
package org.tauceti.graph.resolver;

import org.tauceti.graph.resolver.impl.ClipPathResolverImpl;
import org.tauceti.graph.resolver.impl.FilterResolverImpl;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ResolverRegistry {

	protected static ResolverRegistry INSTANCE = new ResolverRegistry();

	public static ResolverRegistry getInstance() {
		return INSTANCE;
	}

	protected FilterResolver filterResolver;
	protected ClipPathResolver clipPathResolver;

	public ResolverRegistry() {
	}

	/**
	 * 
	 * @return
	 */
	public FilterResolver getFilterResolver() {
		if (this.filterResolver == null) {
			this.filterResolver = new FilterResolverImpl();
		}
		return this.filterResolver;
	}

	/**
	 * 
	 * @param filterResolver
	 */
	public void setFilterResolver(FilterResolver filterResolver) {
		this.filterResolver = filterResolver;
	}

	/**
	 * 
	 * @return
	 */
	public ClipPathResolver getClipPathResolver() {
		if (this.clipPathResolver == null) {
			this.clipPathResolver = new ClipPathResolverImpl();
		}
		return this.clipPathResolver;
	}

	/**
	 * 
	 * @param clipPathResolver
	 */
	public void setClipPathResolver(ClipPathResolver clipPathResolver) {
		this.clipPathResolver = clipPathResolver;
	}
}
