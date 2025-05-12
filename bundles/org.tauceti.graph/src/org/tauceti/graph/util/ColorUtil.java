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
package org.tauceti.graph.util;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ColorUtil {

	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public static String getColorString(int r, int g, int b) {
		r = checkRGB(r);
		g = checkRGB(g);
		b = checkRGB(b);

		return "rgb(" + r + "," + g + "," + b + ")";
	}

	/**
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public static String getColorString(double r, double g, double b) {
		r = checkRGB(r);
		g = checkRGB(g);
		b = checkRGB(b);

		int int_r = (int) (r * 100);
		int int_g = (int) (g * 100);
		int int_b = (int) (b * 100);

		return "rgb(" + int_r + "%," + int_g + "%," + int_b + "%)";
	}

	/**
	 * Check RGB color value [0, 255]
	 * 
	 * @param value
	 * @return
	 */
	public static int checkRGB(int value) {
		if (value < 0) {
			value = 0;
		}
		if (value > 255) {
			value = 255;
		}
		return value;
	}

	/**
	 * Check RGB color percent value [0, 100]
	 * 
	 * @param value
	 * @return
	 */
	public static double checkRGB(double value) {
		if (value < 0) {
			value = 0;
		}
		if (value > 1) {
			value = 1;
		}
		return value;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static double checkOpacity(double value) {
		if (value < 0) {
			value = 0;
		}
		if (value > 1) {
			value = 1;
		}
		return value;
	}
}
