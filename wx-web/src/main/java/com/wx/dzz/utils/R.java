/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wx.dzz.utils;

import org.springframework.lang.NonNull;

import java.util.HashMap;

/**
 * @description: 封装返回结果类
 * @author Mystery Men
 * @create: 2018-09-21 22:42:04
 **/
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R(int code, String msg) {
		put("code", code);
		put("msg", msg);
		put("data", null);
		put("error", false);
	}

	public static R<?> fail() {
		return new R<>(RetEnum.ERROR.getRet(), RetEnum.ERROR.getMsg());
	}

	public static R<Object> fail(String msg) {
		return fail(RetEnum.ERROR.getRet(), msg);
	}

	public static R<Object> fail(int code, String msg) {
		R<Object> r = new R<>(code, msg);
		r.put("error", true);
		r.put("data", null);
		return r;
	}

	public static <T> R<T> fail(T data) {
		R<T> r = new R<>(RetEnum.ERROR.getRet(), RetEnum.ERROR.getMsg());
		r.put("data", data);
		r.put("error", true);
		return r;
	}


	public static <T> R<T> fail(int code, String msg, T data) {
		R<T> r = new R<>(code, msg);
		r.put("data", data);
		r.put("error", true);
		return r;
	}


	public static R<Object> ok() {
		return new R<>(RetEnum.SUCCESS.getRet(), RetEnum.SUCCESS.getMsg());
	}


	public static R<Object> ok(String msg) {
		R<Object> r = new R<>(RetEnum.SUCCESS.getRet(), msg);
		r.put("data", msg);
		r.put("error", false);
		return r;
	}

	public static R<Object> ok(int code, String msg) {
		R<Object> r = new R<>(code, msg);
		r.put("code", code);
		r.put("msg", msg);
		r.put("data", null);
		r.put("error", false);
		return r;
	}


	public static <T> R<T> ok(T data) {
		R<T> r = new R<>(RetEnum.SUCCESS.getRet(), RetEnum.SUCCESS.getMsg());
		r.put("data", data);
		r.put("error", false);
		return r;
	}


	public static <T> R<T> ok(int code, String msg, T data) {
		R<T> r = new R<>(code, msg);
		r.put("data", data);
		r.put("error", false);
		return r;
	}

	public static <T> R<T> ok(String touser,int templateId, String url, T data) {
		R<T> r = new R<>(templateId, url);
		r.put("touser",touser);
		r.put("data", data);
		r.put("error", false);
		return r;
	}


	@NonNull
	@Override
	public R<T> put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
