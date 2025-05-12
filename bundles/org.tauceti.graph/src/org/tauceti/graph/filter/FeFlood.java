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
	<feFlood flood-color="#FFCC99" flood-opacity="1" x="0%" y="0%" width="100%" height="100%" result="flood2"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeFlood extends FilterComponent {

	public static enum FEATURES {
		FLOOD_COLOR, FLOOD_OPACITY
	}

	protected String floodColor;
	protected Integer floodOpacity;

	public FeFlood() {
	}

	public String getFloodColor() {
		return this.floodColor;
	}

	public void setFloodColor(String floodColor) {
		String oldValue = this.floodColor;
		this.floodColor = floodColor;

		if ((oldValue == null && this.floodColor != null) || (oldValue != null && !oldValue.equals(this.floodColor))) {
			notify(this, Notification.SET, FEATURES.FLOOD_COLOR, oldValue, this.floodColor);
		}
	}

	public Integer getFloodOpacity() {
		return this.floodOpacity;
	}

	public void setFloodOpacity(Integer floodOpacity) {
		Integer oldValue = this.floodOpacity;
		this.floodOpacity = floodOpacity;

		if ((oldValue == null && this.floodOpacity != null) || (oldValue != null && !oldValue.equals(this.floodOpacity))) {
			notify(this, Notification.SET, FEATURES.FLOOD_OPACITY, oldValue, this.floodOpacity);
		}
	}
}
