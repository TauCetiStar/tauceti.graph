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
	<feMorphology operator="erode" radius="3 3" x="0%" y="0%" width="100%" height="100%" in="dropShadow3" result="morphology2"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeMorphology extends FilterComponent {

	public static enum FEATURES {
		OPERATOR, RADIUS
	}

	public static String OPERATOR__ERODE = "erode";

	protected String operator;
	protected String radius;

	public FeMorphology() {
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		String oldValue = this.operator;
		this.operator = operator;

		if ((oldValue == null && this.operator != null) || (oldValue != null && !oldValue.equals(this.operator))) {
			notify(this, Notification.SET, FEATURES.OPERATOR, oldValue, this.operator);
		}
	}

	public String getRadius() {
		return this.radius;
	}

	public void setRadius(String radius) {
		String oldValue = this.radius;
		this.radius = radius;

		if ((oldValue == null && this.radius != null) || (oldValue != null && !oldValue.equals(this.radius))) {
			notify(this, Notification.SET, FEATURES.RADIUS, oldValue, this.radius);
		}
	}
}
