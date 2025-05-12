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
package org.tauceti.graph.graphics;

import java.io.Serializable;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Rectangle implements Serializable {

	private static final long serialVersionUID = 1914137888436249032L;

	public int x;
	public int y;
	public int width;
	public int height;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @param bounds
	 */
	public Rectangle(Rectangle bounds) {
		this.x = bounds.x;
		this.y = bounds.y;
		this.width = bounds.width;
		this.height = bounds.height;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setArea(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isEmpty() {
		return (this.width <= 0) || (this.height <= 0);
	}

	public boolean contains(int x, int y) {
		return (x >= this.x) && (y >= this.y) && x < (this.x + this.width) && y < (this.y + this.height);
	}

	public boolean contains(Point pt) {
		return contains(pt.x, pt.y);
	}

	/**
	 * Update this rectangle to be the intersection with the given rectangle.
	 * 
	 * @param rect
	 */
	public void intersect(Rectangle rect) {
		if (this == rect) {
			return;
		}

		int left = x > rect.x ? x : rect.x;
		int top = y > rect.y ? y : rect.y;
		int lhs = x + width;
		int rhs = rect.x + rect.width;
		int right = lhs < rhs ? lhs : rhs;

		lhs = y + height;
		rhs = rect.y + rect.height;
		int bottom = lhs < rhs ? lhs : rhs;

		this.x = right < left ? 0 : left;
		this.y = bottom < top ? 0 : top;
		this.width = right < left ? 0 : right - left;
		this.height = bottom < top ? 0 : bottom - top;
	}

	/**
	 * Get the intersection of two rectangles.
	 * 
	 * @param rect
	 * @return
	 */
	public Rectangle intersection(Rectangle rect) {
		if (this == rect) {
			return new Rectangle(x, y, width, height);
		}

		int left = x > rect.x ? x : rect.x;
		int top = y > rect.y ? y : rect.y;
		int lhs = x + width;
		int rhs = rect.x + rect.width;
		int right = lhs < rhs ? lhs : rhs;

		lhs = y + height;
		rhs = rect.y + rect.height;
		int bottom = lhs < rhs ? lhs : rhs;

		return new Rectangle(right < left ? 0 : left, bottom < top ? 0 : top, right < left ? 0 : right - left, bottom < top ? 0 : bottom - top);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean intersects(int x, int y, int width, int height) {
		return (x < this.x + this.width) && (y < this.y + this.height) && (x + width > this.x) && (y + height > this.y);
	}

	/**
	 * 
	 * @param rect
	 * @return
	 */
	public boolean intersects(Rectangle rect) {
		return rect == this || intersects(rect.x, rect.y, rect.width, rect.height);
	}

	/**
	 * Update this rectangle to be the union with the given rectangle.
	 * 
	 * @param rect
	 */
	public void add(Rectangle rect) {
		int left = x < rect.x ? x : rect.x;
		int top = y < rect.y ? y : rect.y;
		int lhs = x + width;
		int rhs = rect.x + rect.width;
		int right = lhs > rhs ? lhs : rhs;

		lhs = y + height;
		rhs = rect.y + rect.height;
		int bottom = lhs > rhs ? lhs : rhs;

		this.x = left;
		this.y = top;
		this.width = right - left;
		this.height = bottom - top;
	}

	/**
	 * Get a new Rectangle which is the union of this and the given rectangle.
	 * 
	 * @param rect
	 * @return
	 */
	public Rectangle union(Rectangle rect) {
		int left = x < rect.x ? x : rect.x;
		int top = y < rect.y ? y : rect.y;
		int lhs = x + width;
		int rhs = rect.x + rect.width;
		int right = lhs > rhs ? lhs : rhs;

		lhs = y + height;
		rhs = rect.y + rect.height;
		int bottom = lhs > rhs ? lhs : rhs;

		return new Rectangle(left, top, right - left, bottom - top);
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof Rectangle)) {
			return false;
		}
		Rectangle rect = (Rectangle) object;
		return (rect.x == this.x) && (rect.y == this.y) && (rect.width == this.width) && (rect.height == this.height);
	}

	@Override
	public int hashCode() {
		return x ^ y ^ width ^ height;
	}

	@Override
	public String toString() {
		return "Rectangle {" + x + ", " + y + ", " + width + ", " + height + "}"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
}
