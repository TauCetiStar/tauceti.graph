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
package org.tauceti.graph.adapter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Feature {

	protected final static int prime = 31;

	protected Class<?> featureClass;
	protected Field[] featureFields;

	/**
	 * 
	 * @param featureClass
	 * @param featureField
	 */
	public Feature(Class<?> featureClass, Field... featureField) {
		this.featureClass = featureClass;
		this.featureFields = featureField;
	}

	public Class<?> getFeatureClass() {
		return this.featureClass;
	}

	public void setFeatureClass(Class<?> featureClass) {
		this.featureClass = featureClass;
	}

	public Field[] getFeatureFields() {
		return this.featureFields;
	}

	public Set<Field> getFeatureFieldsSet() {
		Set<Field> fieldsSet = new HashSet<Field>();
		if (this.featureFields != null) {
			for (Field featureField : this.featureFields) {
				fieldsSet.add(featureField);
			}
		}
		return fieldsSet;
	}

	public Field getFeatureField() {
		return (this.featureFields != null && this.featureFields.length > 0) ? this.featureFields[0] : null;
	}

	public void setFeatureFields(Field... featureField) {
		this.featureFields = featureField;
	}

	public int getFeatureCount() {
		return this.featureFields != null ? this.featureFields.length : 0;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = prime * result + ((this.featureClass != null) ? this.featureClass.hashCode() : 0);
		if (this.featureFields != null) {
			for (Field featureField : this.featureFields) {
				result = prime * result + ((featureField != null) ? featureField.hashCode() : 0);
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Feature)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		Feature other = (Feature) obj;
		Class<?> otherClazz = other.getFeatureClass();

		Set<Field> otherFeaturesSet = other.getFeatureFieldsSet();
		Set<Field> thisFeaturesSet = this.getFeatureFieldsSet();

		if (this.featureClass != null && this.featureClass.equals(otherClazz) && thisFeaturesSet.equals(otherFeaturesSet)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String text = "{" + this.featureClass.getName() + "} ";
		Set<Field> fieldsSet = this.getFeatureFieldsSet();

		int index = 0;
		Iterator<Field> fieldsItor = fieldsSet.iterator();
		while (fieldsItor.hasNext()) {
			Field field = fieldsItor.next();
			if (index > 0) {
				text += ",";
			}
			text += field.getName();
			index++;
		}
		return text;
	}
}
