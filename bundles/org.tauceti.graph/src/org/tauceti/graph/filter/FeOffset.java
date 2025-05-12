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
	<feOffset dx="15" dy="15" x="0%" y="0%" width="100%" height="100%" in="tile1" result="offset1"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeOffset extends FilterComponent {

	public static enum FEATURES {
		DX, DY
	}

	protected Integer dx;
	protected Integer dy;

	public FeOffset() {
	}

	public Integer getDx() {
		return this.dx;
	}

	public void setDx(Integer dx) {
		Integer oldValue = this.dx;
		this.dx = dx;

		if ((oldValue == null && this.dx != null) || (oldValue != null && !oldValue.equals(this.dx))) {
			notify(this, Notification.SET, FEATURES.DX, oldValue, this.dx);
		}
	}

	public Integer getDy() {
		return this.dy;
	}

	public void setDy(Integer dy) {
		Integer oldValue = this.dy;
		this.dy = dy;

		if ((oldValue == null && this.dy != null) || (oldValue != null && !oldValue.equals(this.dy))) {
			notify(this, Notification.SET, FEATURES.DY, oldValue, this.dy);
		}
	}
}
