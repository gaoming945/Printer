package com.ufgov.util;

import java.lang.reflect.Method;

public class MergsBeanUtil {
	public static Object CopyBeanToBean(Object obj1, Object obj2)
			throws Exception {
		Method[] method1 = obj1.getClass().getMethods();
		Method[] method2 = obj2.getClass().getMethods();
		String methodName1;
		String methodFix1;
		String methodName2;
		String methodFix2;
		for (int i = 0; i < method1.length; i++) {
			methodName1 = method1[i].getName();
			methodFix1 = methodName1.substring(3, methodName1.length());
			if (methodName1.startsWith("get")) {
				for (int j = 0; j < method2.length; j++) {
					methodName2 = method2[j].getName();
					methodFix2 = methodName2.substring(3, methodName2.length());
					if (methodName2.startsWith("set")) {
						if (methodFix2.equals(methodFix1)) {
							Object[] objs1 = new Object[0];
							Object[] objs2 = new Object[1];
							objs2[0] = method1[i].invoke(obj1, objs1);
							if (objs2[0] != null && (!"".equals(objs2[0]))) {
								method2[j].invoke(obj2, objs2);
							}
							continue;
						}
					}
				}
			}
		}
		return obj2;
	}
}