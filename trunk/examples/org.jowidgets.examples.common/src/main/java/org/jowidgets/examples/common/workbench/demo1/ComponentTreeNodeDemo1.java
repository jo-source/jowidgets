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

package org.jowidgets.examples.common.workbench.demo1;

import java.util.LinkedList;
import java.util.List;

import org.jowidgets.api.command.IAction;
import org.jowidgets.api.command.IActionBuilder;
import org.jowidgets.api.command.ICommandExecutor;
import org.jowidgets.api.command.IExecutionContext;
import org.jowidgets.api.model.item.IMenuModel;
import org.jowidgets.api.toolkit.Toolkit;
import org.jowidgets.common.image.IImageConstant;
import org.jowidgets.examples.common.icons.SilkIcons;
import org.jowidgets.examples.common.workbench.base.AbstractComponentTreeNode;
import org.jowidgets.tools.model.item.MenuModel;
import org.jowidgets.workbench.api.IComponent;
import org.jowidgets.workbench.api.IComponentTreeNode;
import org.jowidgets.workbench.api.IComponentTreeNodeContext;

public class ComponentTreeNodeDemo1 extends AbstractComponentTreeNode {

	private final List<IComponentTreeNode> children;

	public ComponentTreeNodeDemo1(final String id, final String label) {
		this(id, label, null, SilkIcons.PAGE_WHITE, new LinkedList<IComponentTreeNode>());
	}

	public ComponentTreeNodeDemo1(final String id, final String label, final List<IComponentTreeNode> children) {
		this(id, label, null, SilkIcons.PAGE_WHITE, children);
	}

	public ComponentTreeNodeDemo1(
		final String id,
		final String label,
		final String tooltip,
		final IImageConstant icon,
		final List<IComponentTreeNode> children) {

		super(id, label, tooltip, icon);

		this.children = children;
	}

	@Override
	public IComponent createComponent() {
		return new ComponentDemo1();
	}

	@Override
	public IMenuModel createPopupMenu() {
		final IMenuModel result = new MenuModel();
		result.addAction(createDeleteComponentAction());
		return result;
	}

	private IAction createDeleteComponentAction() {
		final IActionBuilder actionBuilder = Toolkit.getActionBuilderFactory().create();
		actionBuilder.setText("Delete " + getLabel());
		actionBuilder.setIcon(SilkIcons.FOLDER_DELETE);
		actionBuilder.setCommand(new ICommandExecutor() {

			@Override
			public void execute(final IExecutionContext executionContext) throws Exception {
				final IComponentTreeNodeContext parentTreeNode = getContext().getParent();
				if (parentTreeNode == null) {
					getContext().getWorkbenchApplicationContext().remove(ComponentTreeNodeDemo1.this);
				}
				else {
					parentTreeNode.remove(ComponentTreeNodeDemo1.this);
				}
			}

		});

		return actionBuilder.build();
	}

	@Override
	public List<IComponentTreeNode> createChildren() {
		return new LinkedList<IComponentTreeNode>(children);
	}

}
