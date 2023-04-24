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

/**
 * @author Selton Guedes
 */
public class KaleoActionThreadLocal {

	public static boolean isLocked() {
		return _locked.get();
	}

	public static SafeCloseable lock(long kaleoInstanceId) {
		SafeCloseable safeCloseable = setWithSafeCloseable(kaleoInstanceId);

		_locked.set(true);

		return () -> {
			_locked.set(false);

			safeCloseable.close();
		};
	}

	public static SafeCloseable setWithSafeCloseable(Long kaleoInstanceId) {
		return _kaleoInstanceId.setWithSafeCloseable(kaleoInstanceId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoActionThreadLocal.class);

	private static final CentralizedThreadLocal<Long> _kaleoInstanceId =
		new CentralizedThreadLocal<>(
			CompanyThreadLocal.class + "._kaleoInstanceId",
			() -> CompanyConstants.SYSTEM);
	private static final ThreadLocal<Boolean> _locked =
		new CentralizedThreadLocal<>(
			CompanyThreadLocal.class + "._locked", () -> Boolean.FALSE);

}