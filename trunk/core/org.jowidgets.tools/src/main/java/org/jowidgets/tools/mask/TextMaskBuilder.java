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

package org.jowidgets.tools.mask;

import org.jowidgets.api.mask.ITextMaskBuilder;
import org.jowidgets.api.toolkit.Toolkit;
import org.jowidgets.common.mask.ICharacterMask;
import org.jowidgets.common.mask.ITextMask;

public final class TextMaskBuilder implements ITextMaskBuilder {

	private final ITextMaskBuilder builder;

	public TextMaskBuilder() {
		this.builder = Toolkit.createTextMaskBuilder();
	}

	@Override
	public ITextMaskBuilder defaultPlaceholder(final char placeholder) {
		builder.defaultPlaceholder(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addDelimiterMask(final char placeholder) {
		builder.addDelimiterMask(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addAcceptingAllMask(final char placeholder) {
		builder.addAcceptingAllMask(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addAcceptingMask(final String acceptingRegExp, final char placeholder) {
		builder.addAcceptingMask(acceptingRegExp, placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addRejectingMask(final String rejectingRegExp, final char placeholder) {
		builder.addRejectingMask(rejectingRegExp, placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addCharacterMask(final String acceptingRegExp, final String rejectingRegExp, final char placeholder) {
		builder.addCharacterMask(acceptingRegExp, rejectingRegExp, placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addAcceptingAllMask() {
		builder.addAcceptingAllMask();
		return this;
	}

	@Override
	public ITextMaskBuilder addAcceptingMask(final String acceptingRegExp) {
		builder.addAcceptingMask(acceptingRegExp);
		return this;
	}

	@Override
	public ITextMaskBuilder addRejectingMask(final String rejectingRegExp) {
		builder.addRejectingMask(rejectingRegExp);
		return this;
	}

	@Override
	public ITextMaskBuilder addCharacterMask(final String acceptingRegExp, final String rejectingRegExp) {
		builder.addCharacterMask(acceptingRegExp, rejectingRegExp);
		return this;
	}

	@Override
	public ITextMaskBuilder addCharacterMask(final ICharacterMask mask) {
		builder.addCharacterMask(mask);
		return this;
	}

	@Override
	public ITextMaskBuilder addNumericMask(final char placeholder) {
		builder.addNumericMask(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addNumericMask() {
		builder.addNumericMask();
		return this;
	}

	@Override
	public ITextMaskBuilder addAlphabeticMask(final char placeholder) {
		builder.addAlphabeticMask(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addAlphaNumericMask(final char placeholder) {
		builder.addAlphaNumericMask(placeholder);
		return this;
	}

	@Override
	public ITextMaskBuilder addAlphabeticMask() {
		builder.addAlphabeticMask();
		return this;
	}

	@Override
	public ITextMaskBuilder addAlphaNumericMask() {
		builder.addAlphaNumericMask();
		return this;
	}

	@Override
	public ITextMask build() {
		return builder.build();
	}

}
