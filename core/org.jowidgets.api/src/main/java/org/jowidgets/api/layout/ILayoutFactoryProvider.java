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

package org.jowidgets.api.layout;

import org.jowidgets.common.widgets.layout.ILayouter;

public interface ILayoutFactoryProvider {

	/**
	 * The 'NullLayout' does no layouting at all. It returns
	 * the containers size for min-, max-, and preferred size.
	 * 
	 * The layouting must be completely done by the container then
	 * 
	 * @return The layout factory that produces 'NullLayouts'
	 */
	ILayoutFactory<ILayouter> nullLayout();

	/**
	 * Creates 'PreferredSizeLayouts':
	 * 
	 * layout(): sets the sizes of the controls to the preferred size,
	 * positions must be set by the container
	 * 
	 * getPreferredSize(): get the size needed to layout all controls at its current
	 * position with its preferred size
	 * 
	 * getMinSize(): gets the preferred size
	 * 
	 * getMaxSize(): gets the preferred size
	 * 
	 * @return A layout factory that produces 'PreferredSizeLayouts'
	 */
	ILayoutFactory<ILayouter> preferredSizeLayout();

}
