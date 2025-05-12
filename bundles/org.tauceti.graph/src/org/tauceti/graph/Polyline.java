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

import java.util.ArrayList;
import java.util.List;

import org.tauceti.graph.adapter.Notification;
import org.tauceti.graph.graphics.Point;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Polyline extends Figure {

	public static enum FEATURES {
		POINTS
	}

	protected List<Point> points; // points that compose the lines

	public Polyline() {
		this.points = new ArrayList<Point>();
	}

	public Polyline(List<Point> points) {
		if (points == null) {
			this.points = new ArrayList<Point>();
		} else {
			this.points = points;
		}
	}

	public boolean contains(Point point) {
		if (point == null) {
			return false;
		}
		return this.points.contains(point);
	}

	public List<Point> getPoints() {
		return this.points;
	}

	public void setPoints(List<Point> points) {
		Object oldValue = this.points;
		this.points = points;

		notify(this, Notification.SET, Polyline.FEATURES.POINTS, oldValue, points);
	}

	public Polyline addPoint(Point point) {
		this.points.add(point);

		notify(this, Notification.ADD, Polyline.FEATURES.POINTS, null, point);
		return this;
	}

	public Polyline addPoint(int index, Point point) {
		this.points.add(index, point);

		notify(this, Notification.ADD, Polyline.FEATURES.POINTS, null, point);
		return this;
	}

	public Polyline addPoint(int x, int y) {
		return addPoint(new Point(x, y));
	}

	public Polyline removePoint(Point point) {
		this.points.remove(point);

		notify(this, Notification.REMOVE, Polyline.FEATURES.POINTS, point, null);
		return this;
	}
}
