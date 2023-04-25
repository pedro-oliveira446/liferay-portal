/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.runtime.internal.action;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

/**
 * @author Selton Guedes
 */
public class KaleoActionThreadLocal {

	public static boolean isLocked() {
		return _locked.get();
	}

	public static SafeCloseable lock(long kaleoTimerInstanceTokenId) {
		SafeCloseable safeCloseable = setWithSafeCloseable(kaleoTimerInstanceTokenId);

		_locked.set(true);

		return () -> {
			_locked.set(false);

			safeCloseable.close();
		};
	}

	public static SafeCloseable setWithSafeCloseable(Long kaleoTimerInstanceTokenId) {
		return _kaleoTimerInstanceTokenId.setWithSafeCloseable(kaleoTimerInstanceTokenId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoActionThreadLocal.class);

	private static final CentralizedThreadLocal<Long> _kaleoTimerInstanceTokenId =
		new CentralizedThreadLocal<>(
			KaleoActionThreadLocal.class + "._kaleoTimerInstanceTokenId",
			() -> 0L);
	private static final ThreadLocal<Boolean> _locked =
		new CentralizedThreadLocal<>(
			KaleoActionThreadLocal.class + "._locked", () -> Boolean.FALSE);

}