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
package org.tauceti.graph.filter;

import org.tauceti.graph.FilterComponent;
import org.tauceti.graph.adapter.Notification;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeMergeNode extends FilterComponent {

	public static enum FEATURES {
		IN
	}

	protected String in;

	public FeMergeNode() {
	}

	public String getIn() {
		return this.in;
	}

	public void setIn(String in) {
		String oldValue = this.in;
		this.in = in;

		if ((oldValue == null && this.in != null) || (oldValue != null && !oldValue.equals(this.in))) {
			notify(this, Notification.SET, FEATURES.IN, oldValue, this.in);
		}
	}
}
