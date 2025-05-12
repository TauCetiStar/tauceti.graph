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
	<feConvolveMatrix order="3 3" kernelMatrix="-3 0 0 0 -3 0 0 0 4" divisor="1" bias="1" targetX="0" targetY="0" 
		edgeMode="duplicate" preserveAlpha="true" x="0%" y="0%" width="100%" height="100%" in="colormatrix1" result="convolveMatrix1"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeConvolveMatrix extends FilterComponent {

	public static enum FEATURES {
		ORDER, KERNEL_MATRIX, DIVISOR, BIAS, TARGET_X, TARGET_Y, EDGE_MODE, PRESERVE_ALPHA
	}

	public static String EDGE_MODE__DUPLICATE = "duplicate";

	protected String order;
	protected String kernelMatrix;
	protected Integer divisor;
	protected Integer bias;
	protected Integer targetX;
	protected Integer targetY;
	protected String edgeMode;
	protected Boolean preserveAlpha;

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		String oldValue = this.order;
		this.order = order;

		if ((oldValue == null && this.order != null) || (oldValue != null && !oldValue.equals(this.order))) {
			notify(this, Notification.SET, FEATURES.ORDER, oldValue, this.order);
		}
	}

	public String getKernelMatrix() {
		return this.kernelMatrix;
	}

	public void setKernelMatrix(String kernelMatrix) {
		String oldValue = this.kernelMatrix;
		this.kernelMatrix = kernelMatrix;

		if ((oldValue == null && this.kernelMatrix != null) || (oldValue != null && !oldValue.equals(this.kernelMatrix))) {
			notify(this, Notification.SET, FEATURES.KERNEL_MATRIX, oldValue, this.kernelMatrix);
		}
	}

	public Integer getDivisor() {
		return this.divisor;
	}

	public void setDivisor(Integer divisor) {
		Integer oldValue = this.divisor;
		this.divisor = divisor;

		if ((oldValue == null && this.divisor != null) || (oldValue != null && !oldValue.equals(this.divisor))) {
			notify(this, Notification.SET, FEATURES.DIVISOR, oldValue, this.divisor);
		}
	}

	public Integer getBias() {
		return this.bias;
	}

	public void setBias(Integer bias) {
		Integer oldValue = this.bias;
		this.bias = bias;

		if ((oldValue == null && this.bias != null) || (oldValue != null && !oldValue.equals(this.bias))) {
			notify(this, Notification.SET, FEATURES.BIAS, oldValue, this.bias);
		}
	}

	public Integer getTargetX() {
		return this.targetX;
	}

	public void setTargetX(Integer targetX) {
		Integer oldValue = this.targetX;
		this.targetX = targetX;

		if ((oldValue == null && this.targetX != null) || (oldValue != null && !oldValue.equals(this.targetX))) {
			notify(this, Notification.SET, FEATURES.TARGET_X, oldValue, this.targetX);
		}
	}

	public Integer getTargetY() {
		return this.targetY;
	}

	public void setTargetY(Integer targetY) {
		Integer oldValue = this.targetY;
		this.targetY = targetY;

		if ((oldValue == null && this.targetY != null) || (oldValue != null && !oldValue.equals(this.targetY))) {
			notify(this, Notification.SET, FEATURES.TARGET_Y, oldValue, this.targetY);
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

	public Boolean isPreserveAlpha() {
		return this.preserveAlpha;
	}

	public void setPreserveAlpha(Boolean preserveAlpha) {
		Boolean oldValue = this.preserveAlpha;
		this.preserveAlpha = preserveAlpha;

		if ((oldValue == null && this.preserveAlpha != null) || (oldValue != null && !oldValue.equals(this.preserveAlpha))) {
			notify(this, Notification.SET, FEATURES.PRESERVE_ALPHA, oldValue, this.preserveAlpha);
		}
	}
}
