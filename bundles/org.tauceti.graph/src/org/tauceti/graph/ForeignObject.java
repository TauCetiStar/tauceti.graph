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
import org.tauceti.graph.graphics.DisplayAttribute;
import org.tauceti.graph.graphics.OverflowAttribute;
import org.tauceti.graph.graphics.Rectangle;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ForeignObject extends Figure implements DisplayAttribute, OverflowAttribute {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, BOUNDS, CONTENT
	}

	protected String display;
	protected int x, y, width, height; // top-left point, width, height
	protected String content;

	public ForeignObject() {
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		Object oldValue = this.x;
		this.x = x;

		notify(this, Notification.SET, FEATURES.X, oldValue, x);
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		Object oldValue = this.y;
		this.y = y;

		notify(this, Notification.SET, FEATURES.Y, oldValue, y);
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		Object oldValue = this.width;
		this.width = width;

		notify(this, Notification.SET, FEATURES.WIDTH, oldValue, width);
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		Object oldValue = this.height;
		this.height = height;

		notify(this, Notification.SET, FEATURES.HEIGHT, oldValue, height);
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		String oldValue = this.content;
		this.content = content;

		if (isChanged(oldValue, this.content)) {
			notify(this, Notification.SET, FEATURES.CONTENT, oldValue, height);
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

		notify(this, Notification.SET, ForeignObject.FEATURES.BOUNDS, oldValue, rect);
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
