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
import org.tauceti.graph.graphics.Point;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Line extends Figure {

	public static enum FEATURES {
		X1, Y1, X2, Y2
	}

	protected int x1, y1, x2, y2; // start point and end point

	public Line() {
	}

	/**
	 * 
	 * @param p1
	 * @param p2
	 */
	public Line(Point p1, Point p2) {
		this.x1 = p1.getX();
		this.y1 = p1.getY();
		this.x2 = p2.getX();
		this.y2 = p2.getY();
	}

	/**
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Line(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		Object oldValue = this.x1;
		this.x1 = x1;

		notify(this, Notification.SET, Line.FEATURES.X1, oldValue, x1);
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		Object oldValue = this.y1;
		this.y1 = y1;

		notify(this, Notification.SET, Line.FEATURES.Y1, oldValue, y1);
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		Object oldValue = this.x2;
		this.x2 = x2;

		notify(this, Notification.SET, Line.FEATURES.X2, oldValue, x2);
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		Object oldValue = this.y2;
		this.y2 = y2;

		notify(this, Notification.SET, Line.FEATURES.Y2, oldValue, y2);
	}
}
