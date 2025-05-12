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
public class FeDiffuseLighting extends FilterComponent {

	public static enum FEATURES {
		SURFACE_SCALE, DIFFUSE_CONSTANT, LIGHTING_COLOR
	}

	protected Integer surfaceScale;
	protected Double diffuseConstant;
	protected String lightingColor;

	public FeDiffuseLighting() {
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

	public Double getDiffuseConstant() {
		return this.diffuseConstant;
	}

	public void setDiffuseConstant(Double diffuseConstant) {
		Double oldValue = this.diffuseConstant;
		this.diffuseConstant = diffuseConstant;

		if ((oldValue == null && this.diffuseConstant != null) || (oldValue != null && !oldValue.equals(this.diffuseConstant))) {
			notify(this, Notification.SET, FEATURES.DIFFUSE_CONSTANT, oldValue, this.diffuseConstant);
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
