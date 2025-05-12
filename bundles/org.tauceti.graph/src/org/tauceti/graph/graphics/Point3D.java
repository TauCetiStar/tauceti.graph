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
public class Point3D implements Serializable {

	private static final long serialVersionUID = 2194515351320289880L;

	public long x;
	public long y;
	public long z;

	public Point3D() {
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public long getX() {
		return this.x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return this.y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return this.z;
	}

	public void setZ(long z) {
		this.z = z;
	}

	public String getPositionString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}

	public Point3D copy() {
		return new Point3D(this.x, this.y, this.z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (this.x ^ (this.x >>> 32));
		result = prime * result + (int) (this.y ^ (this.y >>> 32));
		result = prime * result + (int) (this.z ^ (this.z >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point3D other = (Point3D) obj;
		if (this.x != other.x) {
			return false;
		}
		if (this.y != other.y) {
			return false;
		}
		if (this.z != other.z) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Point3D(");
		sb.append("x=").append(this.x);
		sb.append(",y=").append(this.y);
		sb.append(",z=").append(this.z);
		sb.append(")");
		return sb.toString();
	}
}
