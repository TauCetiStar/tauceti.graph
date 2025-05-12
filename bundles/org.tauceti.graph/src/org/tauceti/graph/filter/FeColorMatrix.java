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

/*-
 * <feColorMatrix type="saturate" values="5" x="0%" y="0%" width="100%" height="100%" in="blend1" result="colormatrix1"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeColorMatrix extends FilterComponent {

	public static enum FEATURES {
		TYPE, VALUES
	}

	public static String TYPE__MATRIX = "matrix";

	protected String type;
	protected String values;

	public FeColorMatrix() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		String oldValue = this.type;
		this.type = type;

		if ((oldValue == null && this.type != null) || (oldValue != null && !oldValue.equals(this.type))) {
			notify(this, Notification.SET, FEATURES.TYPE, oldValue, this.type);
		}
	}

	public String getValues() {
		return this.values;
	}

	public void setValues(String values) {
		String oldValue = this.values;
		this.values = values;

		if ((oldValue == null && this.values != null) || (oldValue != null && !oldValue.equals(this.values))) {
			notify(this, Notification.SET, FEATURES.VALUES, oldValue, this.values);
		}
	}
}
