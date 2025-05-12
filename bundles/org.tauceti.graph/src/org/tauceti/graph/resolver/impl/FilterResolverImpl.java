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
package org.tauceti.graph.resolver.impl;

import org.tauceti.graph.Definitions;
import org.tauceti.graph.Figure;
import org.tauceti.graph.Filter;
import org.tauceti.graph.IFigure;
import org.tauceti.graph.SVG;
import org.tauceti.graph.resolver.FilterResolver;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FilterResolverImpl implements FilterResolver {

	public FilterResolverImpl() {
	}

	@Override
	public Filter resolve(Object source, String filterId) {
		if (source == null) {
			throw new IllegalArgumentException("source is null.");
		}
		if (filterId == null) {
			throw new IllegalArgumentException("filterId is null.");
		}

		Filter filter = null;
		if (source instanceof Figure) {
			Figure shape = (Figure) source;
			while (shape != null) {
				if (shape instanceof SVG) {
					for (IFigure child : ((SVG) shape).getChildren()) {
						if (child instanceof Definitions) {
							for (IFigure currChild : ((Definitions) child).getChildren()) {
								if (currChild instanceof Filter && filterId.equals(((Filter) currChild).getId())) {
									filter = (Filter) currChild;
									break;
								}
							}
						}
						if (filter != null) {
							break;
						}
					}
				}
				if (filter != null) {
					break;
				}
				shape = shape.getParent();
			}
		}
		return filter;
	}
}
