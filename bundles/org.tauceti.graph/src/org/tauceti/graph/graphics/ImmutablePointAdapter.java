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
public abstract class ImmutablePointAdapter implements IPoint {

	public ImmutablePointAdapter() {
	}

	@Override
	public void setX(int x) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setY(int y) {
		throw new UnsupportedOperationException();
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

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ImmutablePointAdapter(").append("x=").append(getX()).append(",y=").append(getY()).append(")");
		return sb.toString();
	}
}
