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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PointPair {

	public int x1;
	public int y1;
	public int x2;
	public int y2;

	public PointPair() {
	}

	public PointPair(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return this.x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return this.y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return this.x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return this.y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x1;
		result = prime * result + this.x2;
		result = prime * result + this.y1;
		result = prime * result + this.y2;
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
		PointPair other = (PointPair) obj;
		if (this.x1 != other.x1) {
			return false;
		}
		if (this.x2 != other.x2) {
			return false;
		}
		if (this.y1 != other.y1) {
			return false;
		}
		if (this.y2 != other.y2) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PointPair [x1=" + this.x1 + ", y1=" + this.y1 + ", x2=" + this.x2 + ", y2=" + this.y2 + "]";
	}
}
