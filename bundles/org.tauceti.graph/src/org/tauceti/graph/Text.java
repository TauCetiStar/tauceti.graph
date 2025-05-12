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
public class Text extends Figure implements OverflowAttribute {

	public static final String ANCHOR_START = "start";
	public static final String ANCHOR_MIDDLE = "middle";
	public static final String ANCHOR_END = "end";

	public static enum FEATURES {
		X, Y, DX, DY, TEXT, TEXT_ANCHOR, TEXT_ADJUST, TEXT_LENGTH
	}

	protected int x, y;
	protected int dx, dy;
	protected String text;
	protected String textAnchor;
	protected String lengthAdjust;
	protected String textLength;

	public Text() {
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Text(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public Text(int x, int y, int dx, int dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		String oldValue = this.text;
		this.text = text;

		notify(this, Notification.SET, Text.FEATURES.TEXT, oldValue, text);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		int oldValue = this.x;
		this.x = x;

		if (oldValue != x) {
			notify(this, Notification.SET, Text.FEATURES.X, oldValue, x);
		}
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		int oldValue = this.y;
		this.y = y;

		if (oldValue != y) {
			notify(this, Notification.SET, Text.FEATURES.Y, oldValue, y);
		}
	}

	public int getDx() {
		return this.dx;
	}

	public void setDx(int dx) {
		int oldValue = this.dx;
		this.dx = dx;

		if (oldValue != dx) {
			notify(this, Notification.SET, Text.FEATURES.DX, oldValue, dx);
		}
	}

	public int getDy() {
		return this.dy;
	}

	public void setDy(int dy) {
		int oldValue = this.dy;
		this.dy = dy;

		if (oldValue != dy) {
			notify(this, Notification.SET, Text.FEATURES.DY, oldValue, dy);
		}
	}

	public String getTextAnchor() {
		return this.textAnchor;
	}

	public void setTextAnchor(String textAnchor) {
		Object oldValue = this.textAnchor;
		this.textAnchor = textAnchor;

		if (isChanged(oldValue, textAnchor)) {
			notify(this, Notification.SET, Text.FEATURES.TEXT_ANCHOR, oldValue, textAnchor);
		}
	}

	public String getLengthAdjust() {
		return this.lengthAdjust;
	}

	public void setLengthAdjust(String lengthAdjust) {
		String oldValue = this.lengthAdjust;
		this.lengthAdjust = lengthAdjust;

		if (isChanged(oldValue, lengthAdjust)) {
			notify(this, Notification.SET, Text.FEATURES.TEXT_ADJUST, oldValue, lengthAdjust);
		}
	}

	public String getTextLength() {
		return this.textLength;
	}

	public void setTextLength(String textLength) {
		String oldValue = this.textLength;
		this.textLength = textLength;

		if (isChanged(oldValue, textLength)) {
			notify(this, Notification.SET, Text.FEATURES.TEXT_LENGTH, oldValue, textLength);
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
