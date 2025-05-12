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

import java.util.ArrayList;
import java.util.List;

import org.tauceti.graph.ClipPath;
import org.tauceti.graph.Figure;
import org.tauceti.graph.IFigure;
import org.tauceti.graph.SVG;
import org.tauceti.graph.resolver.ClipPathResolver;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ClipPathResolverImpl implements ClipPathResolver {

	public ClipPathResolverImpl() {
	}

	@Override
	public ClipPath resolve(Object source, String clipPathId) {
		if (source == null) {
			throw new IllegalArgumentException("source is null.");
		}
		if (clipPathId == null) {
			throw new IllegalArgumentException("clipPathId is null.");
		}

		ClipPath clipPath = null;
		if (source instanceof IFigure) {
			IFigure shape = (Figure) source;

			List<IFigure> countered = new ArrayList<IFigure>();
			SVG svg = null;
			while (shape != null) {
				if (shape instanceof SVG) {
					svg = (SVG) shape;
					break;
				}

				clipPath = resolve(countered, shape, clipPathId);
				if (clipPath != null) {
					break;
				}
				shape = shape.getParent();
			}
			if (clipPath == null) {
				if (svg != null) {
					clipPath = resolve(svg, clipPathId);
				}
			}
		}
		return clipPath;
	}

	/**
	 * 
	 * @param countered
	 * @param shape
	 * @param clipPathId
	 * @return
	 */
	protected ClipPath resolve(List<IFigure> countered, IFigure shape, String clipPathId) {
		if (shape == null || countered.contains(shape)) {
			return null;
		}
		countered.add(shape);

		ClipPath clipPath = null;
		if (shape instanceof ClipPath && clipPathId.equals(((ClipPath) shape).getId())) {
			clipPath = (ClipPath) shape;
		}

		if (clipPath == null) {
			if (shape.getChildren() != null) {
				for (IFigure child : shape.getChildren()) {
					clipPath = resolve(countered, child, clipPathId);
					if (clipPath != null) {
						break;
					}
				}
			}
		}
		return clipPath;
	}
}
