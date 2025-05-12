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
package org.tauceti.graph.graphics;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Transform {

	protected String matrix;
	protected int translateX = 0;
	protected int translateY = 0;
	protected int rotate = 0;
	protected double scaleX = 1;
	protected double scaleY = 1;
	protected int skewX = 0;
	protected int skewY = 0;

	public Transform() {
	}

	public Transform(int translateX, int translateY) {
		this.translateX = translateX;
		this.translateY = translateY;
	}

	public Transform matrix(String matrix) {
		this.matrix = matrix;
		return this;
	}

	public Transform translate(int translateX, int translateY) {
		this.translateX = translateX;
		this.translateY = translateY;
		return this;
	}

	public Transform translateX(int translateX) {
		this.translateX = translateX;
		return this;
	}

	public Transform translateY(int translateY) {
		this.translateY = translateY;
		return this;
	}

	public Transform rotate(int rotate) {
		this.rotate = rotate;
		return this;
	}

	public Transform scale(double scale) {
		scale(scale, scale);
		return this;
	}

	public Transform scale(double scaleX, double scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		return this;
	}

	public Transform skewX(int skewX) {
		this.skewX = skewX;
		return this;
	}

	public Transform skewY(int skewY) {
		this.skewY = skewY;
		return this;
	}

	public String getMatrix() {
		return this.matrix;
	}

	public int getTranslateX() {
		return this.translateX;
	}

	public int getTranslateY() {
		return this.translateY;
	}

	public int getRotate() {
		return this.rotate;
	}

	public double getScaleX() {
		return this.scaleX;
	}

	public double getScaleY() {
		return this.scaleY;
	}

	public int getSkewX() {
		return this.skewX;
	}

	public int getSkewY() {
		return this.skewY;
	}
}
