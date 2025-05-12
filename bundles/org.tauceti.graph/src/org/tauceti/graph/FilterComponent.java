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
package org.tauceti.graph;

import org.tauceti.graph.adapter.Notification;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FilterComponent extends Figure {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, IN, IN2, RESULT
	}

	public static String IN__SOURCE_ALPHA 		= "SourceAlpha";
	public static String IN__SOURCE_GRAPHIC 	= "SourceGraphic";
	public static String IN__BLUR_ALPHA 		= "bluralpha";
	public static String IN__OFFSET_BLUR 		= "offsetBlur";
	public static String IN__COLORED_BLUR 		= "coloredBlur";

	public static String RESULT__BLUR 			= "blur";
	public static String RESULT__BLUR_ALPHA 	= "bluralpha";
	public static String RESULT__OFFSET_BLUR 	= "offsetBlur";
	public static String RESULT__COLORED_BLUR 	= "coloredBlur";

	protected String x;
	protected String y;
	protected String width;
	protected String height;
	protected String in;
	protected String in2;
	protected String result;

	public FilterComponent() {
	}

	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		String oldValue = this.x;
		this.x = x;

		if ((oldValue == null && this.x != null) || (oldValue != null && !oldValue.equals(this.x))) {
			notify(this, Notification.SET, FEATURES.X, oldValue, this.x);
		}
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		String oldValue = this.y;
		this.y = y;

		if ((oldValue == null && this.y != null) || (oldValue != null && !oldValue.equals(this.y))) {
			notify(this, Notification.SET, FEATURES.Y, oldValue, this.y);
		}
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		String oldValue = this.width;
		this.width = width;

		if ((oldValue == null && this.width != null) || (oldValue != null && !oldValue.equals(this.width))) {
			notify(this, Notification.SET, FEATURES.WIDTH, oldValue, this.width);
		}
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		String oldValue = this.height;
		this.height = height;

		if ((oldValue == null && this.height != null) || (oldValue != null && !oldValue.equals(this.height))) {
			notify(this, Notification.SET, FEATURES.HEIGHT, oldValue, this.height);
		}
	}

	public String getIn() {
		return this.in;
	}

	public void setIn(String in) {
		String oldValue = this.in;
		this.in = in;

		if ((oldValue == null && this.in != null) || (oldValue != null && !oldValue.equals(this.in))) {
			notify(this, Notification.SET, FEATURES.IN, oldValue, this.in);
		}
	}

	public String getIn2() {
		return this.in2;
	}

	public void setIn2(String in2) {
		String oldValue = this.in2;
		this.in2 = in2;

		if ((oldValue == null && this.in2 != null) || (oldValue != null && !oldValue.equals(this.in2))) {
			notify(this, Notification.SET, FEATURES.IN2, oldValue, this.in2);
		}
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		String oldValue = this.result;
		this.result = result;

		if ((oldValue == null && this.result != null) || (oldValue != null && !oldValue.equals(this.result))) {
			notify(this, Notification.SET, FEATURES.RESULT, oldValue, this.result);
		}
	}
}
