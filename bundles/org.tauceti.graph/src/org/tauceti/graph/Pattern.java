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
package org.tauceti.graph;

import org.tauceti.graph.adapter.Notification;
import org.tauceti.graph.graphics.OverflowAttribute;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Pattern extends BoundedShape implements OverflowAttribute {

	public static enum FEATURES {
		PATTERN_UNITS
	}

	protected String patternUnits;

	public Pattern() {
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Pattern(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
	}

	public String getPatternUnits() {
		return this.patternUnits;
	}

	public void setPatternUnits(String patternUnits) {
		Object oldValue = this.patternUnits;
		this.patternUnits = patternUnits;

		if (isChanged(oldValue, this.patternUnits)) {
			notify(this, Notification.SET, Pattern.FEATURES.PATTERN_UNITS, oldValue, this.patternUnits);
		}
	}

	protected String overflow;

	@Override
	public String getOverflow() {
		return this.overflow;
	}

	@Override
	public void setOverflow(String overflow) {
		String oldValue = this.overflow;
		this.overflow = overflow;

		if (isChanged(oldValue, overflow)) {
			notify(this, Notification.SET, Figure.FEATURES.OVERFLOW, oldValue, overflow);
		}
	}
}
