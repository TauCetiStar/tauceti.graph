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
import org.tauceti.graph.graphics.OverflowAttribute;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ImageShape extends BoundedShape implements OverflowAttribute {

	public static enum FEATURES {
		HREF, PRESERVE_ASPECT_RATIO
	}

	// x: Positions the image horizontally from the origin.
	// y: Positions the image vertically from the origin.
	// width: The width the image renders at. Unlike HTML's <img>, this attribute is required.
	// height: The height the image renders at. Unlike HTML's <img>, this attribute is required.
	// href and xlink:href: Points at a URL for the image file.
	// preserveAspectRatio: Controls how the image is scaled.
	protected String href;
	protected String preserveAspectRatio;

	public ImageShape() {
	}

	/**
	 * 
	 * @param id
	 * @param href
	 */
	public ImageShape(String id, String href) {
		this.id = id;
		this.href = href;
	}

	/**
	 * 
	 * @param id
	 * @param href
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ImageShape(String id, String href, int x, int y, int width, int height) {
		this.id = id;
		this.href = href;
		setBounds(x, y, width, height);
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		Object oldValue = this.href;
		this.href = href;

		if (isChanged(oldValue, this.href)) {
			notify(this, Notification.SET, ImageShape.FEATURES.HREF, oldValue, this.href);
		}
	}

	public String getPreserveAspectRatio() {
		return this.preserveAspectRatio;
	}

	public void setPreserveAspectRatio(String preserveAspectRatio) {
		String oldValue = this.preserveAspectRatio;
		this.preserveAspectRatio = preserveAspectRatio;

		if (isChanged(oldValue, this.preserveAspectRatio)) {
			notify(this, Notification.SET, ImageShape.FEATURES.PRESERVE_ASPECT_RATIO, oldValue, this.preserveAspectRatio);
		}
	}

	protected String overflow;

	@Override
	public String getOverflow() {
		return this.overflow;
	}

	@Override
	public void setOverflow(String overflow) {
		String oldValue = this.overflow;
		this.overflow = overflow;

		if (isChanged(oldValue, overflow)) {
			notify(this, Notification.SET, Figure.FEATURES.OVERFLOW, oldValue, overflow);
		}
	}
}
