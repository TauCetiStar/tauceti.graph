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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ViewBox {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT
	}

	protected int x, y, width, height;

	public ViewBox() {
	}

	public ViewBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public ViewBox copy() {
		return new ViewBox(this.x, this.y, this.width, this.height);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.height;
		result = prime * result + this.width;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewBox other = (ViewBox) obj;
		if (this.height != other.height)
			return false;
		if (this.width != other.width)
			return false;
		if (this.x != other.x)
			return false;
		if (this.y != other.y)
			return false;
		return true;
	}

	public String getViewBoxString() {
		return this.x + " " + this.y + " " + this.width + " " + this.height;
	}

	public String toSimpleString() {
		return "(" + this.x + ", " + this.y + ", " + this.width + ", " + this.height + ")";
	}

	@Override
	public String toString() {
		return "ViewBox[x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + "]";
	}
}
