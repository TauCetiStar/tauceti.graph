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
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Rect extends Figure {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, BOUND, RX, RY
	}

	protected int x, y; // top-left point
	protected int width, height; // width, height
	protected int rx = -1; // corner radius
	protected int ry = -1; // corner radius y

	public Rect() {
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rect(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		int oldValue = this.x;
		this.x = x;

		if (oldValue != x) {
			notify(this, Notification.SET, Rect.FEATURES.X, oldValue, x);
		}
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		int oldValue = this.y;
		this.y = y;

		if (oldValue != y) {
			notify(this, Notification.SET, Rect.FEATURES.Y, oldValue, y);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		int oldValue = this.width;
		this.width = width;

		if (oldValue != width) {
			notify(this, Notification.SET, Rect.FEATURES.WIDTH, oldValue, width);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		int oldValue = this.height;
		this.height = height;

		if (oldValue != height) {
			notify(this, Notification.SET, Rect.FEATURES.HEIGHT, oldValue, height);
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

		if (isChanged(oldValue, rect)) {
			notify(this, Notification.SET, Rect.FEATURES.BOUND, oldValue, rect);
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
		setBounds(new Rectangle(x, y, width, height));
	}

	public int getRx() {
		return this.rx;
	}

	public void setRx(int rx) {
		int oldValue = this.rx;
		this.rx = rx;

		if (oldValue != rx) {
			notify(this, Notification.SET, Rect.FEATURES.RX, oldValue, rx);
		}
	}

	public int getRy() {
		return this.ry;
	}

	public void setRy(int ry) {
		int oldValue = this.ry;
		this.ry = ry;

		if (oldValue != ry) {
			notify(this, Notification.SET, Rect.FEATURES.RY, oldValue, ry);
		}
	}
}
