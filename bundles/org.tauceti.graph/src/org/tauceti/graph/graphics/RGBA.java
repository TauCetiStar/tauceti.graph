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

import java.io.Serializable;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class RGBA implements Serializable {

	private static final long serialVersionUID = 1317453442123530061L;

	public final RGB rgb;
	public int alpha;

	/**
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param alpha
	 */
	public RGBA(int red, int green, int blue, int alpha) {
		if ((alpha > 255) || (alpha < 0)) {
			throw new IllegalArgumentException();
		}
		this.rgb = new RGB(red, green, blue);
		this.alpha = alpha;
	}

	/**
	 * 
	 * @param hue
	 * @param saturation
	 * @param brightness
	 * @param alpha
	 */
	public RGBA(float hue, float saturation, float brightness, float alpha) {
		if ((alpha > 255) || (alpha < 0)) {
			throw new IllegalArgumentException();
		}
		this.rgb = new RGB(hue, saturation, brightness);
		this.alpha = (int) (alpha + 0.5);
	}

	/**
	 * Get the hue, saturation and brightness.
	 * 
	 * @return
	 */
	public float[] getHSBA() {
		float[] hsb = this.rgb.getHSB();
		return new float[] { hsb[0], hsb[1], hsb[2], this.alpha };
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof RGBA)) {
			return false;
		}
		RGBA rgba = (RGBA) object;
		return (rgba.rgb.red == this.rgb.red) && (rgba.rgb.green == this.rgb.green) && (rgba.rgb.blue == this.rgb.blue) && (rgba.alpha == this.alpha);
	}

	@Override
	public int hashCode() {
		return (this.alpha << 24) | (this.rgb.blue << 16) | (this.rgb.green << 8) | this.rgb.red;
	}

	@Override
	public String toString() {
		return "RGBA {" + this.rgb.red + ", " + this.rgb.green + ", " + this.rgb.blue + ", " + this.alpha + "}";
	}
}
