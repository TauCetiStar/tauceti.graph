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
package org.tauceti.graph.extensions;

import javax.xml.namespace.QName;

import org.tauceti.graph.Figure;
import org.tauceti.graph.util.SVGConstants;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class ScriptDeclaration implements SVGExtensionAttribute {

	protected Figure container;
	protected String eventName;
	protected String functionName;
	protected Object[] args;

	public ScriptDeclaration() {
	}

	@Override
	public QName getAttributeType() {
		return SVGConstants.SCRIPT_DECLARATION_QNAME;
	}

	@Override
	public void setContainer(Figure shape) {
		this.container = shape;
	}

	@Override
	public Figure getContainer() {
		return this.container;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String name) {
		this.eventName = name;
	}

	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Object[] getArgs() {
		return this.args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	/**
	 * 
	 * @param eventName
	 * @param functionName
	 * @param args
	 */
	public void setDeclaration(String eventName, String functionName, Object[] args) {
		this.eventName = eventName;
		this.functionName = functionName;
		this.args = args;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeclaration() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.eventName).append("=\"");
		if (this.functionName != null) {
			sb.append(this.functionName).append("(");
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					if (i == 0) {
						sb.append(this.args[i]);
					} else {
						sb.append(", " + this.args[i]);
					}
				}
			}
			sb.append(")");
		}
		sb.append("\"");
		return sb.toString();
	}

	public String getFunction() {
		StringBuffer sb = new StringBuffer();
		if (this.functionName != null) {
			sb.append(this.functionName).append("(");
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					if (i == 0) {
						sb.append(this.args[i]);
					} else {
						sb.append(", " + this.args[i]);
					}
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
}
