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
	<feDiffuseLighting surfaceScale="5" diffuseConstant="0.75" lighting-color="#BBF900" x="0%" y="0%" width="100%" height="100%" in="specularLighting1" result="diffuseLighting1">
		<feDistantLight azimuth="3" elevation="100"/>
	</feDiffuseLighting>
*/
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeDistantLight extends FilterComponent {

	public static enum FEATURES {
		AZIMUTH, ELEVATION
	}

	protected Integer azimuth;
	protected Integer elevation;

	public FeDistantLight() {
	}

	public Integer getAzimuth() {
		return this.azimuth;
	}

	public void setAzimuth(Integer azimuth) {
		Integer oldValue = this.azimuth;
		this.azimuth = azimuth;

		if ((oldValue == null && this.azimuth != null) || (oldValue != null && !oldValue.equals(this.azimuth))) {
			notify(this, Notification.SET, FEATURES.AZIMUTH, oldValue, this.azimuth);
		}
	}

	public Integer getElevation() {
		return this.elevation;
	}

	public void setElevation(Integer elevation) {
		Integer oldValue = this.elevation;
		this.elevation = elevation;

		if ((oldValue == null && this.elevation != null) || (oldValue != null && !oldValue.equals(this.elevation))) {
			notify(this, Notification.SET, FEATURES.ELEVATION, oldValue, this.elevation);
		}
	}
}
