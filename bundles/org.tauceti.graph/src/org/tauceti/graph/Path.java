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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Path extends Figure {

	public static enum FEATURES {
		D
	}

	protected String d;

	public Path() {
	}

	/**
	 * 
	 * @param d
	 */
	public Path(String d) {
		this.d = d;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		String oldValue = this.d;
		this.d = d;

		if (isChanged(oldValue, this.d)) {
			notify(this, Notification.SET, Path.FEATURES.D, oldValue, this.d);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
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
		Path other = (Path) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		return true;
	}
}
