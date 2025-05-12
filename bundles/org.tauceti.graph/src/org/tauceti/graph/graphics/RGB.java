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
public class RGB implements Serializable {

	private static final long serialVersionUID = -8910305011503284254L;

	public int red;
	public int green;
	public int blue;

	/**
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public RGB(int red, int green, int blue) {
		if ((red > 255) || (red < 0) || (green > 255) || (green < 0) || (blue > 255) || (blue < 0)) {
			throw new IllegalArgumentException();
		}
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * 
	 * @param hue
	 * @param saturation
	 * @param brightness
	 */
	public RGB(float hue, float saturation, float brightness) {
		if (hue < 0 || hue > 360 || saturation < 0 || saturation > 1 || brightness < 0 || brightness > 1) {
			throw new IllegalArgumentException();
		}

		float r, g, b;
		if (saturation == 0) {
			r = g = b = brightness;

		} else {
			if (hue == 360) {
				hue = 0;
			}
			hue /= 60;
			int i = (int) hue;
			float f = hue - i;
			float p = brightness * (1 - saturation);
			float q = brightness * (1 - saturation * f);
			float t = brightness * (1 - saturation * (1 - f));
			switch (i) {
			case 0:
				r = brightness;
				g = t;
				b = p;
				break;
			case 1:
				r = q;
				g = brightness;
				b = p;
				break;
			case 2:
				r = p;
				g = brightness;
				b = t;
				break;
			case 3:
				r = p;
				g = q;
				b = brightness;
				break;
			case 4:
				r = t;
				g = p;
				b = brightness;
				break;
			case 5:
			default:
				r = brightness;
				g = p;
				b = q;
				break;
			}
		}
		this.red = (int) (r * 255 + 0.5);
		this.green = (int) (g * 255 + 0.5);
		this.blue = (int) (b * 255 + 0.5);
	}

	/**
	 * Get the hue, saturation and brightness.
	 * 
	 * @return
	 */
	public float[] getHSB() {
		float r = this.red / 255f;
		float g = this.green / 255f;
		float b = this.blue / 255f;
		float max = Math.max(Math.max(r, g), b);
		float min = Math.min(Math.min(r, g), b);
		float delta = max - min;
		float hue = 0;
		float brightness = max;
		float saturation = max == 0 ? 0 : (max - min) / max;
		if (delta != 0) {
			if (r == max) {
				hue = (g - b) / delta;
			} else {
				if (g == max) {
					hue = 2 + (b - r) / delta;
				} else {
					hue = 4 + (r - g) / delta;
				}
			}
			hue *= 60;
			if (hue < 0) {
				hue += 360;
			}
		}
		return new float[] { hue, saturation, brightness };
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (!(object instanceof RGB)) {
			return false;
		}
		RGB rgb = (RGB) object;
		return (rgb.red == this.red) && (rgb.green == this.green) && (rgb.blue == this.blue);
	}

	@Override
	public int hashCode() {
		return (this.blue << 16) | (this.green << 8) | this.red;
	}

	@Override
	public String toString() {
		return "RGB {" + this.red + ", " + this.green + ", " + this.blue + "}";
	}
}
