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
	<feSpecularLighting surfaceScale="5" specularConstant="0.75" specularExponent="20" lighting-color="#BBF900" x="0%" y="0%" width="100%" height="100%" in="componentTransfer" result="specularLighting1">
    	<feDistantLight azimuth="3" elevation="100"/>
  	</feSpecularLighting>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeSpecularLighting extends FilterComponent {

	public static enum FEATURES {
		SURFACE_SCALE, SPECULAR_CONSTANT, SPECULAR_EXPONENT, LIGHTING_COLOR
	}

	protected Integer surfaceScale;
	protected Double specularConstant;
	protected Integer specularExponent;
	protected String lightingColor;

	public FeSpecularLighting() {
	}

	public Integer getSurfaceScale() {
		return this.surfaceScale;
	}

	public void setSurfaceScale(Integer surfaceScale) {
		Integer oldValue = this.surfaceScale;
		this.surfaceScale = surfaceScale;

		if ((oldValue == null && this.surfaceScale != null) || (oldValue != null && !oldValue.equals(this.surfaceScale))) {
			notify(this, Notification.SET, FEATURES.SURFACE_SCALE, oldValue, this.surfaceScale);
		}
	}

	public Double getSpecularConstant() {
		return this.specularConstant;
	}

	public void setSpecularConstant(Double specularConstant) {
		Double oldValue = this.specularConstant;
		this.specularConstant = specularConstant;

		if ((oldValue == null && this.specularConstant != null) || (oldValue != null && !oldValue.equals(this.specularConstant))) {
			notify(this, Notification.SET, FEATURES.SPECULAR_CONSTANT, oldValue, this.specularConstant);
		}
	}

	public Integer getSpecularExponent() {
		return this.specularExponent;
	}

	public void setSpecularExponent(Integer specularExponent) {
		Integer oldValue = this.specularExponent;
		this.specularExponent = specularExponent;

		if ((oldValue == null && this.specularExponent != null) || (oldValue != null && !oldValue.equals(this.specularExponent))) {
			notify(this, Notification.SET, FEATURES.SPECULAR_EXPONENT, oldValue, this.specularExponent);
		}
	}

	public String getLightingColor() {
		return this.lightingColor;
	}

	public void setLightingColor(String lightingColor) {
		String oldValue = this.lightingColor;
		this.lightingColor = lightingColor;

		if ((oldValue == null && this.lightingColor != null) || (oldValue != null && !oldValue.equals(this.lightingColor))) {
			notify(this, Notification.SET, FEATURES.LIGHTING_COLOR, oldValue, this.lightingColor);
		}
	}
}
