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
	<feDropShadow stdDeviation="5 5" in="blur4" dx="10" dy="10" flood-color="#1F3646" flood-opacity="1" x="0%" y="0%" width="100%" height="100%" result="dropShadow3"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeDropShadow extends FilterComponent {

	public static enum FEATURES {
		DX, DY, STD_DEVIATION, FLOOD_COLOR, FLOOD_OPACITY
	}

	protected Double dx;
	protected Double dy;
	protected Double stdDeviation;
	protected String floodColor;
	protected Double floodOpacity;

	public FeDropShadow() {
	}

	public Double getDx() {
		return this.dx;
	}

	public void setDx(Double dx) {
		Double oldValue = this.dx;
		this.dx = dx;

		if ((oldValue == null && this.dx != null) || (oldValue != null && !oldValue.equals(this.dx))) {
			notify(this, Notification.SET, FEATURES.DX, oldValue, this.dx);
		}
	}

	public Double getDy() {
		return this.dy;
	}

	public void setDy(Double dy) {
		Double oldValue = this.dy;
		this.dy = dy;

		if ((oldValue == null && this.dy != null) || (oldValue != null && !oldValue.equals(this.dy))) {
			notify(this, Notification.SET, FEATURES.DY, oldValue, this.dy);
		}
	}

	public Double getStdDeviation() {
		return this.stdDeviation;
	}

	public void setStdDeviation(Double stdDeviation) {
		Double oldValue = this.stdDeviation;
		this.stdDeviation = stdDeviation;

		if ((oldValue == null && this.stdDeviation != null) || (oldValue != null && !oldValue.equals(this.stdDeviation))) {
			notify(this, Notification.SET, FEATURES.STD_DEVIATION, oldValue, this.stdDeviation);
		}
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

	public Double getFloodOpacity() {
		return this.floodOpacity;
	}

	public void setFloodOpacity(Double floodOpacity) {
		Double oldValue = this.floodOpacity;
		this.floodOpacity = floodOpacity;

		if ((oldValue == null && this.floodOpacity != null) || (oldValue != null && !oldValue.equals(this.floodOpacity))) {
			notify(this, Notification.SET, FEATURES.FLOOD_OPACITY, oldValue, this.floodOpacity);
		}
	}
}
