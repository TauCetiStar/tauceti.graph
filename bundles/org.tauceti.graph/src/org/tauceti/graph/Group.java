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
import org.tauceti.graph.graphics.BoundsAttribute;
import org.tauceti.graph.graphics.Rectangle;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Group extends Figure implements /* DisplayAttribute, */ BoundsAttribute {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, BOUND
	}

	protected int x, y, width, height; // top-left point, width, height

	public Group() {
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Group(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @param bounds
	 */
	public Group(Rectangle bounds) {
		this.x = bounds.getX();
		this.y = bounds.getY();
		this.width = bounds.getWidth();
		this.height = bounds.getHeight();
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		int oldValue = this.x;
		this.x = x;

		if (oldValue != this.x) {
			notify(this, Notification.SET, Group.FEATURES.X, oldValue, this.x);
		}
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		int oldValue = this.y;
		this.y = y;

		if (oldValue != y) {
			notify(this, Notification.SET, Group.FEATURES.Y, oldValue, this.y);
		}
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		int oldValue = this.width;
		this.width = width;

		if (oldValue != this.width) {
			notify(this, Notification.SET, Group.FEATURES.WIDTH, oldValue, this.width);
		}
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		int oldValue = this.height;
		this.height = height;

		if (oldValue != this.height) {
			notify(this, Notification.SET, Group.FEATURES.HEIGHT, oldValue, this.height);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	/**
	 * 
	 * @param bound
	 */
	@Override
	public void setBounds(Rectangle bound) {
		Rectangle oldValue = new Rectangle(this.x, this.y, this.width, this.height);

		this.x = bound.getX();
		this.y = bound.getY();
		this.width = bound.getWidth();
		this.height = bound.getHeight();

		getTransform().translate(this.x, this.y);

		notify(this, Notification.SET, Group.FEATURES.BOUND, oldValue, bound);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		setBounds(new Rectangle(x, y, width, height));
	}
}

// "display" attribute applies to: 'svg', 'g', 'switch', 'a', 'foreignObject'
// Initial: inline
// Inherited: no
// When setting "display" to 'none':
// (1) it causes the container and all of its children to be invisible; thus, it acts on groups of elements as a group.
// (2) the given element does not become part of the rendering tree.
// (3) the element receives no events
// (4) indicates that the given element and its children shall not be rendered directly (i.e., those elements are not present in the rendering
// tree). Any value other than none or inherit indicates that the given element shall be rendered by the SVG user agent.
// (5) do not take up space in text layout operations, do not receive events, and do not contribute to bounding box and clipping paths
// calculations.
// protected String display;
