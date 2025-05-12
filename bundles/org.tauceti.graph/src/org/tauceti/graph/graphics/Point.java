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

import org.tauceti.graph.util.GraphicsUtil;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Point implements IPoint, Serializable {

	private static final long serialVersionUID = -8172419083103016800L;

	public static Point Size_2000x750 	= new Point(2000, 750);
	public static Point Size_1800x650 	= new Point(1800, 650);
	public static Point Size_1300x850 	= new Point(1300, 850);
	public static Point Size_1200x750 	= new Point(1200, 750);
	public static Point Size_900x550 	= new Point(900, 550);
	public static Point Size_900x800 	= new Point(900, 800);
	public static Point Size_1950x1120 	= new Point(1950, 1120);
	public static Point Size_1200x698 	= new Point(1200, 698);

	public int x, y;

	public Point() {
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(IPoint p) {
		this.x = p.getX();
		this.y = p.getY();
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

	public double getXYRatio() {
		return this.x / this.y;
	}

	public Point copy() {
		return new Point(this.x, this.y);
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof IPoint)) {
			return false;
		}

		IPoint other = (IPoint) object;
		if (getX() == other.getX() && getY() == other.getY()) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + getX();
		hash = 31 * hash + getY();
		return hash;
	}

	public String toSimpleString() {
		StringBuffer sb = new StringBuffer();
		sb.append("(").append(getX()).append(", ").append(getY()).append(")");
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Point(");
		sb.append("x=").append(getX());
		sb.append(",y=").append(getY());
		sb.append(")");
		return sb.toString();
	}

	public void shiftX(int amount) {
		if (amount != 0) {
			this.x += amount;
		}
	}

	public void shiftY(int amount) {
		if (amount != 0) {
			this.y += amount;
		}
	}

	/**
	 * 
	 * @param target
	 * @return
	 */
	public double getAngle(Point target) {
		return GraphicsUtil.getAngle(this, target);
	}
}
