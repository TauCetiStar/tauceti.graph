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
	<feTurbulence type="turbulence" baseFrequency="0.015 0.1" numOctaves="2" seed="2" stitchTiles="stitch" x="0%" y="0%" width="100%" height="100%" result="turbulence1"/>
 */
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeTurbulence extends FilterComponent {

	public static enum FEATURES {
		TYPE, BASE_FREQUENCY, NUM_OCTAVES, SEED, STITCH_TILES
	}

	public static String TYPE__TURBULENCE = "turbulence";

	protected String type;
	protected String baseFrequency;
	protected Integer numOctaves;
	protected Integer seed;
	protected String stitchTiles;

	public FeTurbulence() {
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		String oldValue = this.type;
		this.type = type;

		if ((oldValue == null && this.type != null) || (oldValue != null && !oldValue.equals(this.type))) {
			notify(this, Notification.SET, FEATURES.TYPE, oldValue, this.type);
		}
	}

	public String getBaseFrequency() {
		return this.baseFrequency;
	}

	public void setBaseFrequency(String baseFrequency) {
		String oldValue = this.baseFrequency;
		this.baseFrequency = baseFrequency;

		if ((oldValue == null && this.baseFrequency != null) || (oldValue != null && !oldValue.equals(this.baseFrequency))) {
			notify(this, Notification.SET, FEATURES.BASE_FREQUENCY, oldValue, this.baseFrequency);
		}
	}

	public Integer getNumOctaves() {
		return this.numOctaves;
	}

	public void setNumOctaves(Integer numOctaves) {
		Integer oldValue = this.numOctaves;
		this.numOctaves = numOctaves;

		if ((oldValue == null && this.numOctaves != null) || (oldValue != null && !oldValue.equals(this.numOctaves))) {
			notify(this, Notification.SET, FEATURES.NUM_OCTAVES, oldValue, this.numOctaves);
		}
	}

	public Integer getSeed() {
		return this.seed;
	}

	public void setSeed(Integer seed) {
		Integer oldValue = this.seed;
		this.seed = seed;

		if ((oldValue == null && this.seed != null) || (oldValue != null && !oldValue.equals(this.seed))) {
			notify(this, Notification.SET, FEATURES.SEED, oldValue, this.seed);
		}
	}

	public String getStitchTiles() {
		return this.stitchTiles;
	}

	public void setStitchTiles(String stitchTiles) {
		String oldValue = this.stitchTiles;
		this.stitchTiles = stitchTiles;

		if ((oldValue == null && this.stitchTiles != null) || (oldValue != null && !oldValue.equals(this.stitchTiles))) {
			notify(this, Notification.SET, FEATURES.STITCH_TILES, oldValue, this.stitchTiles);
		}
	}
}
