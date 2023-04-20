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
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;

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
		long currentKaleoInstanceId = _kaleoInstanceId.get();

		_setKaleoInstanceId(kaleoInstanceId);

		SafeCloseable kaleoInstanceSafeCloseable =
			_kaleoInstanceId.setWithSafeCloseable(kaleoInstanceId);

		return () -> {
			_kaleoInstanceId.set(currentKaleoInstanceId);

			kaleoInstanceSafeCloseable.close();
		};
	}

	private static KaleoInstance _fetchKaleoInstance(long kaleoInstanceId) {
		KaleoInstance kaleoInstance = null;

		try {
			kaleoInstance = KaleoInstanceLocalServiceUtil.fetchKaleoInstance(
				kaleoInstanceId);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception);
			}
		}

		return kaleoInstance;
	}

	private static boolean _setKaleoInstanceId(Long kaleoInstanceId) {
		if (kaleoInstanceId.equals(_kaleoInstanceId.get())) {
			return false;
		}

		if (isLocked()) {
			throw new UnsupportedOperationException(
				"CompanyThreadLocal modification is not allowed");
		}

		if (_log.isDebugEnabled()) {
			_log.debug("setCompanyId " + kaleoInstanceId);
		}

		if (kaleoInstanceId > 0) {
			_kaleoInstanceId.set(kaleoInstanceId);

			try {
				KaleoInstance kaleoInstance = _fetchKaleoInstance(
					kaleoInstanceId);

				if (kaleoInstance == null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"No guest user was found for company " +
								kaleoInstanceId);
					}
				}
			}
			catch (Exception exception) {
				_log.error(exception);
			}
		}

		return true;
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