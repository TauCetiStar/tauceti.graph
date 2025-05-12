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
	<feGaussianBlur stdDeviation="3 10" x="0%" y="0%" width="100%" height="100%" in="SourceGraphic" edgeMode="none" result="blur4"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeGaussianBlur extends FilterComponent {

	public static enum FEATURES {
		STD_DEVIATION, EDGE_MODE
	}

	protected String stdDeviation;
	protected String edgeMode;
	protected String value;

	public FeGaussianBlur() {
	}

	public String getStdDeviation() {
		return this.stdDeviation;
	}

	public void setStdDeviation(String stdDeviation) {
		String oldValue = this.stdDeviation;
		this.stdDeviation = stdDeviation;

		if ((oldValue == null && this.stdDeviation != null) || (oldValue != null && !oldValue.equals(this.stdDeviation))) {
			notify(this, Notification.SET, FEATURES.STD_DEVIATION, oldValue, this.stdDeviation);
		}
	}

	public String getEdgeMode() {
		return this.edgeMode;
	}

	public void setEdgeMode(String edgeMode) {
		String oldValue = this.edgeMode;
		this.edgeMode = edgeMode;

		if ((oldValue == null && this.edgeMode != null) || (oldValue != null && !oldValue.equals(this.edgeMode))) {
			notify(this, Notification.SET, FEATURES.EDGE_MODE, oldValue, this.edgeMode);
		}
	}
}
