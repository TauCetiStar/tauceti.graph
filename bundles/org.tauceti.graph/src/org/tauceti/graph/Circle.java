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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Circle extends Figure {

	public static enum FEATURES {
		CX, CY, R
	}

	protected int cx, cy, r; // center and radius

	public Circle() {
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param r
	 */
	public Circle(int cx, int cy, int r) {
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}

	public int getCenterX() {
		return this.cx;
	}

	public void setCenterX(int cx) {
		int oldValue = this.cx;
		this.cx = cx;

		if (oldValue != this.cx) {
			notify(this, Notification.SET, Circle.FEATURES.CX, oldValue, cx);
		}
	}

	public int getCenterY() {
		return this.cy;
	}

	public void setCenterY(int cy) {
		int oldValue = this.cy;
		this.cy = cy;

		if (oldValue != this.cy) {
			notify(this, Notification.SET, Circle.FEATURES.CY, oldValue, cy);
		}
	}

	public int getRadius() {
		return this.r;
	}

	public void setRaduis(int r) {
		int oldValue = this.r;
		this.r = r;

		if (oldValue != this.r) {
			notify(this, Notification.SET, Circle.FEATURES.R, oldValue, r);
		}
	}
}
