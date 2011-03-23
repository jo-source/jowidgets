/*
 * Copyright (c) 2011, grossmann
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 * * Neither the name of the jo-widgets.org nor the
 *   names of its contributors may be used to endorse or promote products
 *   derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL jo-widgets.org BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package org.jowidgets.workbench.toolkit.impl;

import java.util.LinkedList;
import java.util.List;

import org.jowidgets.api.model.IListModelListener;
import org.jowidgets.util.Assert;
import org.jowidgets.workbench.api.IComponentTreeNode;
import org.jowidgets.workbench.api.IComponentTreeNodeContainerContext;
import org.jowidgets.workbench.toolkit.api.IComponentNodeContainerModel;
import org.jowidgets.workbench.toolkit.api.IComponentNodeModel;
import org.jowidgets.workbench.toolkit.api.WorkbenchToolkit;

class ComponentNodeContainer {

	private final IComponentNodeContainerModel model;
	private final List<IComponentTreeNode> createdChildren;

	ComponentNodeContainer(final IComponentNodeContainerModel model) {
		Assert.paramNotNull(model, "model");
		this.createdChildren = new LinkedList<IComponentTreeNode>();

		this.model = model;
	}

	void initialize(final IComponentTreeNodeContainerContext context) {
		for (final IComponentNodeModel nodeModel : model.getChildren()) {
			final IComponentTreeNode componentNode = WorkbenchToolkit.getWorkbenchPartFactory().componentNode(nodeModel);
			context.add(componentNode);
			createdChildren.add(componentNode);
		}

		model.addListModelListener(new IListModelListener() {
			@Override
			public void childRemoved(final int index) {
				final IComponentTreeNode componentNode = createdChildren.remove(index);
				if (componentNode != null) {
					context.remove(componentNode);
				}
			}

			@Override
			public void childAdded(final int index) {
				final IComponentNodeModel nodeModel = model.getChildren().get(index);
				final IComponentTreeNode componentNode = WorkbenchToolkit.getWorkbenchPartFactory().componentNode(nodeModel);
				context.add(index, componentNode);
				createdChildren.add(index, componentNode);
			}
		});
	}

	protected IComponentNodeContainerModel getModel() {
		return model;
	}

}
