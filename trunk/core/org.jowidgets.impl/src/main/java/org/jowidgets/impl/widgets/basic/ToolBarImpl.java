/*
 * Copyright (c) 2010, grossmann
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

package org.jowidgets.impl.widgets.basic;

import java.util.LinkedList;
import java.util.List;

import org.jowidgets.api.widgets.IComponent;
import org.jowidgets.api.widgets.IContainer;
import org.jowidgets.api.widgets.IPopupMenu;
import org.jowidgets.api.widgets.IToolBar;
import org.jowidgets.api.widgets.IToolBarButton;
import org.jowidgets.api.widgets.IToolBarContainerItem;
import org.jowidgets.api.widgets.IToolBarItem;
import org.jowidgets.api.widgets.IToolBarPopupButton;
import org.jowidgets.api.widgets.IToolBarToggleButton;
import org.jowidgets.api.widgets.descriptor.IToolBarButtonDescriptor;
import org.jowidgets.api.widgets.descriptor.IToolBarContainerItemDescriptor;
import org.jowidgets.api.widgets.descriptor.IToolBarPopupButtonDescriptor;
import org.jowidgets.api.widgets.descriptor.IToolBarToggleButtonDescriptor;
import org.jowidgets.api.widgets.descriptor.setup.IContainerSetup;
import org.jowidgets.api.widgets.descriptor.setup.IItemSetup;
import org.jowidgets.api.widgets.descriptor.setup.IToolBarSetup;
import org.jowidgets.common.widgets.descriptor.IWidgetDescriptor;
import org.jowidgets.impl.base.delegate.ControlDelegate;
import org.jowidgets.impl.widgets.basic.factory.internal.util.ColorSettingsInvoker;
import org.jowidgets.impl.widgets.basic.factory.internal.util.VisibiliySettingsInvoker;
import org.jowidgets.impl.widgets.common.wrapper.ToolBarSpiWrapper;
import org.jowidgets.spi.widgets.IToolBarButtonSpi;
import org.jowidgets.spi.widgets.IToolBarContainerItemSpi;
import org.jowidgets.spi.widgets.IToolBarItemSpi;
import org.jowidgets.spi.widgets.IToolBarPopupButtonSpi;
import org.jowidgets.spi.widgets.IToolBarSpi;
import org.jowidgets.spi.widgets.IToolBarToggleButtonSpi;
import org.jowidgets.util.Assert;

public class ToolBarImpl extends ToolBarSpiWrapper implements IToolBar {

	private final ControlDelegate controlDelegate;
	private final List<IToolBarItem> children;

	public ToolBarImpl(final IToolBarSpi widget, final IToolBarSetup setup) {
		super(widget);
		this.controlDelegate = new ControlDelegate();

		this.children = new LinkedList<IToolBarItem>();

		VisibiliySettingsInvoker.setVisibility(setup, this);
		ColorSettingsInvoker.setColors(setup, this);
	}

	@Override
	public IContainer getParent() {
		return controlDelegate.getParent();
	}

	@Override
	public void setParent(final IComponent parent) {
		controlDelegate.setParent(parent);
	}

	@Override
	public boolean isReparentable() {
		return controlDelegate.isReparentable();
	}

	@Override
	public IPopupMenu createPopupMenu() {
		return new PopupMenuImpl(getWidget().createPopupMenu(), this);
	}

	@Override
	public List<IToolBarItem> getChildren() {
		return new LinkedList<IToolBarItem>(children);
	}

	@Override
	public boolean remove(final IToolBarItem item) {
		Assert.paramNotNull(item, "item");

		final int index = children.indexOf(item);
		if (index != -1) {
			if (item instanceof IDisposeable) {
				((IDisposeable) item).dispose();
			}
			children.remove(index);
			getWidget().remove(index);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void removeAll() {
		for (final IToolBarItem item : getChildren()) {
			remove(item);
		}
	}

	@Override
	public IToolBarItem addSeparator() {
		return addSeparator(null);
	}

	@Override
	public IToolBarItem addSeparator(final int index) {
		if (index < 0 || index > children.size()) {
			throw new IllegalArgumentException("Index must be between '0' and '" + children.size() + "'.");
		}
		return addSeparator(Integer.valueOf(index));
	}

	private IToolBarItem addSeparator(final Integer index) {
		final IToolBarItemSpi toolBarItemSpi = getWidget().addSeparator(index);
		return new ToolBarItemImpl(this, toolBarItemSpi);
	}

	@Override
	public <WIDGET_TYPE extends IToolBarItem> WIDGET_TYPE addItem(final IWidgetDescriptor<? extends WIDGET_TYPE> descriptor) {
		return addItem(null, descriptor);
	}

	@Override
	public <WIDGET_TYPE extends IToolBarItem> WIDGET_TYPE addItem(
		final int index,
		final IWidgetDescriptor<? extends WIDGET_TYPE> descriptor) {

		if (index < 0 || index > children.size()) {
			throw new IllegalArgumentException("Index must be between '0' and '" + children.size() + "'.");
		}

		return addItem(Integer.valueOf(index), descriptor);
	}

	@SuppressWarnings("unchecked")
	private <WIDGET_TYPE extends IToolBarItem> WIDGET_TYPE addItem(
		final Integer index,
		final IWidgetDescriptor<? extends WIDGET_TYPE> descriptor) {

		Assert.paramNotNull(descriptor, "descriptor");

		WIDGET_TYPE result = null;

		if (IToolBarButtonDescriptor.class.isAssignableFrom(descriptor.getDescriptorInterface())) {
			final IToolBarButtonSpi toolBarButtonSpi = getWidget().addToolBarButton(index);
			final IToolBarButton toolBarButton = new ToolBarButtonImpl(this, toolBarButtonSpi, (IItemSetup) descriptor);
			result = (WIDGET_TYPE) toolBarButton;
		}
		else if (IToolBarToggleButtonDescriptor.class.isAssignableFrom(descriptor.getDescriptorInterface())) {
			final IToolBarToggleButtonSpi toolBarToggleButtonSpi = getWidget().addToolBarToggleButton(index);
			final IToolBarToggleButton toolBarToggleButton = new ToolBarToggleButtonImpl(
				this,
				toolBarToggleButtonSpi,
				(IItemSetup) descriptor);
			result = (WIDGET_TYPE) toolBarToggleButton;
		}
		else if (IToolBarPopupButtonDescriptor.class.isAssignableFrom(descriptor.getDescriptorInterface())) {
			final IToolBarPopupButtonSpi toolBarPopupSpi = getWidget().addToolBarPopupButton(index);
			final IToolBarPopupButton toolBarPopupButton = new ToolBarPopupButtonImpl(
				this,
				toolBarPopupSpi,
				(IItemSetup) descriptor);
			result = (WIDGET_TYPE) toolBarPopupButton;
		}
		else if (IToolBarContainerItemDescriptor.class.isAssignableFrom(descriptor.getDescriptorInterface())) {
			final IToolBarContainerItemSpi toolBarContainerItemSpi = getWidget().addToolBarContainer(index);
			final IToolBarContainerItem toolBarContainerItem = new ToolBarContainerItemImpl(
				this,
				toolBarContainerItemSpi,
				(IContainerSetup) descriptor);
			result = (WIDGET_TYPE) toolBarContainerItem;
		}
		else {
			throw new IllegalArgumentException("Descriptor with type '" + descriptor.getClass().getName() + "' is not supported");
		}

		addToChildren(index, result);

		return result;
	}

	private void addToChildren(final Integer index, final IToolBarItem item) {
		if (index != null) {
			children.add(index.intValue(), item);
		}
		else {
			children.add(item);
		}
	}

}
