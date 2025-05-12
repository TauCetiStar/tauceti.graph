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
public class Rectangle3D implements Serializable {

	private static final long serialVersionUID = -8158630556304216354L;

	public int x;
	public int y;
	public int z;
	public int width;
	public int height;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param width
	 * @param height
	 */
	public Rectangle3D(int x, int y, int z, int width, int height) {
		this.x = x;
		this.y = y;
		this.z = z;
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

	public int getZ() {
		return this.z;
	}

	public void setZ(int z) {
		this.z = z;
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

	public void setArea(int x, int y, int z, int width, int height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
	}

	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof Rectangle3D)) {
			return false;
		}
		Rectangle3D rect3 = (Rectangle3D) object;
		return (rect3.x == this.x) && (rect3.y == this.y) && (rect3.z == this.z) && (rect3.width == this.width) && (rect3.height == this.height);
	}

	public int hashCode() {
		return this.x ^ this.y ^ this.z ^ this.width ^ this.height;
	}

	public String toString() {
		return "Rectangle3D {" + x + ", " + y + ", " + z + ", " + width + ", " + height + "}";
	}
}
