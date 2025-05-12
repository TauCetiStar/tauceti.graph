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
import org.tauceti.graph.graphics.Rectangle;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class BoundedShape extends Figure {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, BOUND
	}

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public BoundedShape() {
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		int oldValue = this.x;
		this.x = x;

		if (oldValue != this.x) {
			notify(this, Notification.SET, BoundedShape.FEATURES.X, oldValue, x);
		}
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		int oldValue = this.y;
		this.y = y;

		if (oldValue != this.y) {
			notify(this, Notification.SET, BoundedShape.FEATURES.Y, oldValue, y);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		int oldValue = this.width;
		this.width = width;

		if (oldValue != width) {
			notify(this, Notification.SET, BoundedShape.FEATURES.WIDTH, oldValue, width);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		int oldValue = this.height;
		this.height = height;

		if (oldValue != this.height) {
			notify(this, Notification.SET, BoundedShape.FEATURES.HEIGHT, oldValue, height);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	/**
	 * 
	 * @param rect
	 */
	public void setBounds(Rectangle rect) {
		Rectangle oldValue = new Rectangle(this.x, this.y, this.width, this.height);
		this.x = rect.getX();
		this.y = rect.getY();
		this.width = rect.getWidth();
		this.height = rect.getHeight();

		if (oldValue.x != this.x || oldValue.y != this.y || oldValue.width != this.width || oldValue.height != this.height) {
			notify(this, Notification.SET, BoundedShape.FEATURES.BOUND, oldValue, rect);
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void setBounds(int x, int y, int width, int height) {
		int oldX = this.x;
		int oldY = this.y;
		int oldW = this.width;
		int oldH = this.height;

		boolean isChanged = false;
		if (this.x != x) {
			this.x = x;
			isChanged = true;
		}
		if (this.y != y) {
			this.y = y;
			isChanged = true;
		}
		if (this.width != width) {
			this.width = width;
			isChanged = true;
		}
		if (this.height != height) {
			this.height = height;
			isChanged = true;
		}

		if (isChanged) {
			Rectangle oldValue = new Rectangle(oldX, oldY, oldW, oldH);
			Rectangle newValue = new Rectangle(this.x, this.y, this.width, this.height);
			notify(this, Notification.SET, BoundedShape.FEATURES.BOUND, oldValue, newValue);
		}
	}
}
