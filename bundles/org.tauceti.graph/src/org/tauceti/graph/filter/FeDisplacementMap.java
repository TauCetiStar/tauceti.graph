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
	<feDisplacementMap in="SourceGraphic" in2="morphology2" scale="20" xChannelSelector="R" yChannelSelector="B" x="0%" y="0%" width="100%" height="100%" result="displacementMap"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeDisplacementMap extends FilterComponent {

	public static enum FEATURES {
		SCALE, X_CHANNEL_SELECTOR, Y_CHANNEL_SELECTOR
	}

	protected Integer scale;
	protected String xChannelSelector;
	protected String yChannelSelector;

	public FeDisplacementMap() {
	}

	public Integer getScale() {
		return this.scale;
	}

	public void setScale(Integer scale) {
		Integer oldValue = this.scale;
		this.scale = scale;

		if ((oldValue == null && this.scale != null) || (oldValue != null && !oldValue.equals(this.scale))) {
			notify(this, Notification.SET, FEATURES.SCALE, oldValue, this.scale);
		}
	}

	public String getXChannelSelector() {
		return this.xChannelSelector;
	}

	public void setXChannelSelector(String xChannelSelector) {
		String oldValue = this.xChannelSelector;
		this.xChannelSelector = xChannelSelector;

		if ((oldValue == null && this.xChannelSelector != null) || (oldValue != null && !oldValue.equals(this.xChannelSelector))) {
			notify(this, Notification.SET, FEATURES.X_CHANNEL_SELECTOR, oldValue, this.xChannelSelector);
		}
	}

	public String getYChannelSelector() {
		return this.yChannelSelector;
	}

	public void setYChannelSelector(String yChannelSelector) {
		String oldValue = this.yChannelSelector;
		this.yChannelSelector = yChannelSelector;

		if ((oldValue == null && this.yChannelSelector != null) || (oldValue != null && !oldValue.equals(this.yChannelSelector))) {
			notify(this, Notification.SET, FEATURES.Y_CHANNEL_SELECTOR, oldValue, this.yChannelSelector);
		}
	}
}
