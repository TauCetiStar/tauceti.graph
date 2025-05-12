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
public class Filter extends Figure {

	public static enum FEATURES {
		X, Y, WIDTH, HEIGHT, FILTER_UNITS, PRIMITIVE_UNITS, COLOR_INTERPOLATION_FILTERS
	}

	public static String FILTER_UNITS__OBJECT_BOUNDING_BOX = "objectBoundingBox";
	public static String PRIMITIVE_UNITS__USER_SPACE_ON_USE = "userSpaceOnUse";
	public static String COLOR_INTERPOLATION_FILTERS__LINEAR_RGB = "linearRGB";

	protected String x; // e.g. "-20%"
	protected String y; // e.g. "-20%"
	protected String width; // e.g. "140%"
	protected String height; // e.g. "140%"
	protected String filterUnits; // e.g. "objectBoundingBox"
	protected String primitiveUnits; // e.g. "userSpaceOnUse"
	protected String colorInterpolationFilters; // e.g. "linearRGB"

	/**
	 * 
	 * @param id
	 */
	public Filter(String id) {
		this.id = id;
	}

	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		String oldValue = this.x;
		this.x = x;

		if (isChanged(oldValue, this.x)) {
			notify(this, Notification.SET, FEATURES.X, oldValue, this.x);
		}
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		String oldValue = this.y;
		this.y = y;

		if (isChanged(oldValue, this.y)) {
			notify(this, Notification.SET, FEATURES.Y, oldValue, this.y);
		}
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		String oldValue = this.width;
		this.width = width;

		if (isChanged(oldValue, this.width)) {
			notify(this, Notification.SET, FEATURES.WIDTH, oldValue, this.width);
		}
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		String oldValue = this.height;
		this.height = height;

		if (isChanged(oldValue, this.height)) {
			notify(this, Notification.SET, FEATURES.HEIGHT, oldValue, this.height);
		}
	}

	public String getFilterUnits() {
		return this.filterUnits;
	}

	public void setFilterUnits(String filterUnits) {
		String oldValue = this.filterUnits;
		this.filterUnits = filterUnits;

		if (isChanged(oldValue, this.filterUnits)) {
			notify(this, Notification.SET, FEATURES.FILTER_UNITS, oldValue, this.filterUnits);
		}
	}

	public String getPrimitiveUnits() {
		return this.primitiveUnits;
	}

	public void setPrimitiveUnits(String primitiveUnits) {
		String oldValue = this.primitiveUnits;
		this.primitiveUnits = primitiveUnits;

		if (isChanged(oldValue, this.primitiveUnits)) {
			notify(this, Notification.SET, FEATURES.PRIMITIVE_UNITS, oldValue, this.primitiveUnits);
		}
	}

	public String getColorInterpolationFilters() {
		return this.colorInterpolationFilters;
	}

	public void setColorInterpolationFilters(String colorInterpolationFilters) {
		String oldValue = this.colorInterpolationFilters;
		this.colorInterpolationFilters = colorInterpolationFilters;

		if (isChanged(oldValue, this.colorInterpolationFilters)) {
			notify(this, Notification.SET, FEATURES.COLOR_INTERPOLATION_FILTERS, oldValue, this.colorInterpolationFilters);
		}
	}
}
