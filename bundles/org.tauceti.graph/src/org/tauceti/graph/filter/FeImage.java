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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeImage extends FilterComponent {

	public static enum FEATURES {
		HREF, PRESERVE_ASPECT_RATIO, CROSS_ORIGIN
	}

	protected String href;
	protected String preserveAspectRatio;
	protected String crossOrigin;

	public FeImage() {
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		String oldValue = this.href;
		this.href = href;

		if ((oldValue == null && this.href != null) || (oldValue != null && !oldValue.equals(this.href))) {
			notify(this, Notification.SET, FEATURES.HREF, oldValue, this.href);
		}
	}

	public String getPreserveAspectRatio() {
		return this.preserveAspectRatio;
	}

	public void setPreserveAspectRatio(String preserveAspectRatio) {
		String oldValue = this.preserveAspectRatio;
		this.preserveAspectRatio = preserveAspectRatio;

		if ((oldValue == null && this.preserveAspectRatio != null) || (oldValue != null && !oldValue.equals(this.preserveAspectRatio))) {
			notify(this, Notification.SET, FEATURES.PRESERVE_ASPECT_RATIO, oldValue, this.preserveAspectRatio);
		}
	}

	public String getCrossOrigin() {
		return this.crossOrigin;
	}

	public void setCrossOrigin(String crossOrigin) {
		String oldValue = this.crossOrigin;
		this.crossOrigin = crossOrigin;

		if ((oldValue == null && this.crossOrigin != null) || (oldValue != null && !oldValue.equals(this.crossOrigin))) {
			notify(this, Notification.SET, FEATURES.CROSS_ORIGIN, oldValue, this.crossOrigin);
		}
	}
}
