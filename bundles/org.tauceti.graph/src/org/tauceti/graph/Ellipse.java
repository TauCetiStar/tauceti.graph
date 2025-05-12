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
public class Ellipse extends Figure {

	public static enum FEATURES {
		CX, CY, RX, RY
	}

	protected int cx, cy, rx, ry; // center, x radius and y radius

	public Ellipse() {
	}

	/**
	 * 
	 * @param cx
	 * @param cy
	 * @param rx
	 * @param ry
	 */
	public Ellipse(int cx, int cy, int rx, int ry) {
		this.cx = cx;
		this.cy = cy;
		this.rx = rx;
		this.ry = ry;
	}

	public int getCenterX() {
		return this.cx;
	}

	public void setCenterX(int cx) {
		int oldValue = this.cx;
		this.cx = cx;

		if (oldValue != this.cx) {
			notify(this, Notification.SET, Ellipse.FEATURES.CX, oldValue, this.cx);
		}
	}

	public int getCenterY() {
		return this.cy;
	}

	public void setCenterY(int cy) {
		int oldValue = this.cy;
		this.cy = cy;

		if (oldValue != this.cy) {
			notify(this, Notification.SET, Ellipse.FEATURES.CY, oldValue, this.cy);
		}
	}

	public int getRadiusX() {
		return this.rx;
	}

	public void setRaduisX(int rx) {
		int oldValue = this.rx;
		this.rx = rx;

		if (oldValue != this.rx) {
			notify(this, Notification.SET, Ellipse.FEATURES.RX, oldValue, this.rx);
		}
	}

	public int getRadiusY() {
		return this.ry;
	}

	public void setRaduisY(int ry) {
		int oldValue = this.ry;
		this.ry = ry;

		if (oldValue != this.ry) {
			notify(this, Notification.SET, Ellipse.FEATURES.RY, oldValue, this.ry);
		}
	}
}
