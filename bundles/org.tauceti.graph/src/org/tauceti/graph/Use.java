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
public class Use extends Figure {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, HREF, SHAPE
	}

	protected int x, y, width, height; // top-left point, width, height
	protected String href;
	protected Figure shape;

	public Use() {
	}

	/**
	 * 
	 * @param parent
	 */
	public Use(Figure parent) {
		parent.add(this);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		int oldValue = this.x;
		this.x = x;

		if (oldValue != this.x) {
			notify(this, Notification.SET, FEATURES.X, oldValue, this.x);
		}
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		int oldValue = this.y;
		this.y = y;

		if (oldValue != y) {
			notify(this, Notification.SET, FEATURES.Y, oldValue, this.y);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		int oldValue = this.width;
		this.width = width;

		if (oldValue != this.width) {
			notify(this, Notification.SET, FEATURES.WIDTH, oldValue, this.width);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		int oldValue = this.height;
		this.height = height;

		if (oldValue != this.height) {
			notify(this, Notification.SET, FEATURES.HEIGHT, oldValue, this.height);
		}
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		String oldValue = this.href;
		this.href = href;

		if ((oldValue == null && this.href != null) || (oldValue != null && !oldValue.equals(this.href))) {
			notify(this, Notification.SET, FEATURES.HREF, oldValue, this.href);
		}
	}

	public Figure getShape() {
		return this.shape;
	}

	public void setShape(Figure shape) {
		Object oldValue = this.shape;
		this.shape = shape;

		if ((oldValue == null && this.shape != null) || (oldValue != null && !oldValue.equals(shape))) {
			notify(this, Notification.SET, FEATURES.SHAPE, oldValue, this.shape);
		}
	}
}
